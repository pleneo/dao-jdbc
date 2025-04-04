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

        System.out.println("\n\n--------- Seller insert ---------");
        Seller newSeller = new Seller(null, "Diogo", "diogo@diogo.com", new Date(), 5000.00, dep);
//        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());

        System.out.println("\n\n--------- Seller Update  ---------");
        seller = sellerDao.findById(1);
        seller.setName("SpiderMan!");
        sellerDao.update(seller);
        System.out.println("Updated! Updated seller = " + seller);

        System.out.println("\n\n--------- Seller DELETE  ---------");
        seller = sellerDao.findById(10);
        sellerDao.deleteById(10);
        System.out.println("Deleted seller = " + seller);



    }
}
