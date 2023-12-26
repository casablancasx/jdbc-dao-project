package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("========== TEST 1 ============ Find By Id");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("========== TEST 2 ============ Find By Department");
        Department department = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller obj : list){
            System.out.println(obj);
        }

        System.out.println("========== TEST 3 ============ Find ALL ");
        List<Seller> list2 = sellerDao.findAll();
        for (Seller obj : list2){
            System.out.println(obj);
        }

        System.out.println("========== TEST 3 ============ INSERT ");
        Seller seller1 = new Seller(null,"Greg","greg@gmail.com",new Date(),200.00,department);
        sellerDao.insert(seller1);
    }
}
