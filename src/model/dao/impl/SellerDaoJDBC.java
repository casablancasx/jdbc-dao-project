package model.dao.impl;

import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {
    private Connection connection;
    public SellerDaoJDBC(Connection connection){
        this.connection = connection;
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
        return null;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        return null;
    }
}
