package application;

import model.dao.DaoFactory;
import model.dao.IDepartmentDao;
import model.dao.ISellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Department dep = new Department(1, "Books");


//        ISellerDao sellerDao = DaoFactory.createSellerDao();

//        System.out.println("--------- Seller findById ---------");
//
//        Seller seller = sellerDao.findById(3);
//        System.out.println(seller);
//        System.out.println("\n\n--------- Seller findByDepartment ---------");
//
//        List<Seller> sellers = sellerDao.findByDepartment(2);
//        for(Seller s : sellers){
//            System.out.println(s);
//        }
//
//        System.out.println("\n\n--------- Seller findAll ---------");
//
//        sellers = sellerDao.findAll();
//        for(Seller s : sellers){
//            System.out.println(s);
//        }
//
//        System.out.println("\n\n--------- Seller insert ---------");
//        Seller newSeller = new Seller(null, "Diogo", "diogo@diogo.com", new Date(), 5000.00, dep);
////        sellerDao.insert(newSeller);
//        System.out.println("Inserted! New id = " + newSeller.getId());
//
//        System.out.println("\n\n--------- Seller Update  ---------");
//        seller = sellerDao.findById(1);
//        seller.setName("SpiderMan!");
//        sellerDao.update(seller);
//        System.out.println("Updated! Updated seller = " + seller);
//
//        System.out.println("\n\n--------- Seller DELETE  ---------");
//        seller = sellerDao.findById(10);
//        sellerDao.deleteById(10);
//        System.out.println("Deleted seller = " + seller);


        IDepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("\n\n\n\n\n--------- Department findById  ---------");
        dep = departmentDao.findById(1);
        System.out.println(dep);

        System.out.println("\n\n--------- Department findAll  ---------");
        List<Department> departments = departmentDao.findAll();
        for(Department d : departments){
            System.out.println(d);
        }

        System.out.println("\n\n--------- Department Insert  ---------");
        dep = new Department(null, "Garden");
        departmentDao.insert(dep);
        System.out.println("Inserted successfully");
        System.out.println(dep);

        System.out.println("\n\n--------- Department Update  ---------");
        dep = new Department(4, "Sodas");
        departmentDao.update(dep);

        System.out.println("\n\n--------- Department Delete  ---------");
        dep = departmentDao.findById(11);
        departmentDao.deleteById(11);
        System.out.println("Deleted department = " + dep);



    }
}
