package application;

import model.dao.DaoFactory;
import model.dao.ISellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Department dep = new Department(1, "Books");

        Seller seller = new Seller(1, "Joao", "joao@joao.com", new Date(), 3000.0, dep );

        ISellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println(seller);

    }
}
