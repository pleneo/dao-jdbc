package model.dao;

import db.DB;
import model.dao.implementation.SellerDaoJDBC;

public class DaoFactory {

    //gof pattern factory?
    public static ISellerDao createSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }
}
