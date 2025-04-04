package model.dao.implementation;

import db.DB;
import db.DBException;
import model.dao.IDepartmentDao;
import model.entities.Department;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements IDepartmentDao {
    private Connection conn;
    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO department (Name) VALUE (?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, obj.getName());
            int rowsAffected = st.executeUpdate();
            if(rowsAffected>0){
                rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            }else {
                throw new DBException("Unexpected Error! Insertion not realized.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement(
                    "UPDATE department SET Name = ? WHERE Id = ?", Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            int rows = st.executeUpdate();
            
            if(rows<1){
                throw new DBException("Department Update was not realized.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement(
                    "DELETE FROM department WHERE Id = ?"
            );

            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement("SELECT * FROM department WHERE Id = ?");
            st.setInt(1, id);

            rs = st.executeQuery();
            if(rs.next()){
                Department dep = instantiateDepartment(rs);
                return dep;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return null;
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Department> departments = new ArrayList<>();

        try{
            st = conn.prepareStatement("SELECT * FROM department");
            rs = st.executeQuery();
            while (rs.next()){
                Department dep = instantiateDepartment(rs);
                departments.add(dep);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return departments;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
}
