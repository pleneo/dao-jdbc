package application;

import model.dao.DaoFactory;
import model.dao.ISellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Department dep = new Department(1, "Books");


        ISellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("--------- Seller findById ---------");

        Seller seller = sellerDao.findById(3);
        System.out.println(seller);
        System.out.println("\n\n--------- Seller findByDepartment ---------");

        List<Seller> sellers = sellerDao.findByDepartment(2);
        for(Seller s : sellers){
            System.out.println(s);
        }

        System.out.println("\n\n--------- Seller findAll ---------");

        sellers = sellerDao.findAll();
        for(Seller s : sellers){
            System.out.println(s);
        }


    }
}
