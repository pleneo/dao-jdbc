package model.dao.implementation;

import db.DB;
import model.dao.ISellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements ISellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Department dep = instantiateDepartment(rs);

                Seller seller = instantiateSeller(dep ,rs);
                return seller;
            }
            return null;
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

        return null;
    }

    @Override
    public List<Seller> findByDepartment(Integer id) {
        List<Seller> sellers = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE department.Id = ? "
                    + "ORDER BY Name"
            );
            st.setInt(1, id);
            rs = st.executeQuery();
            //MINHA IMPLEMENTAÇÃO:
            // o departamento deve ficar fora do while, pois não faz sentido
            // recriar o departamento toda vez que o while rodar, só quem deve
            // ser criado é o seller, que irá mudar.
//            Department dep = null;
//            if(rs.next()){
//                 dep = instantiateDepartment(rs);
//            }
//            do{
//                Seller seller = instantiateSeller(dep, rs);
//                sellers.add(seller);
//            }while(rs.next());


//            IMPLEMENTAÇÃO PROFESSOR:
            Map<Integer, Department> map = new HashMap<>();

            while(rs.next()){
                Department dep = map.get(rs.getInt("DepartmentId"));

                if(dep == null){
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Seller seller = instantiateSeller(dep, rs);
                sellers.add(seller);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return sellers;
    }

    @Override
    public List<Seller> findAll() {
        List<Seller> sellers = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "ORDER BY Name"
            );
            rs = st.executeQuery();

            Map<Integer, Department> map = new HashMap<>();

            while(rs.next()){
                Department dep = map.get(rs.getInt("DepartmentId"));

                if(dep == null){
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Seller seller = instantiateSeller(dep, rs);
                sellers.add(seller);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return sellers;
    }


    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    private Seller instantiateSeller(Department dep, ResultSet rs) throws SQLException {
        Seller seller = new Seller();
        seller.setId(rs.getInt("Id"));
        seller.setName(rs.getString("Name"));
        seller.setEmail(rs.getString("Email"));
        seller.setBaseSalary(rs.getDouble("BaseSalary"));
        seller.setBirthDate(rs.getDate("BirthDate"));
        seller.setDepartment(dep);
        return seller;
    }


}
