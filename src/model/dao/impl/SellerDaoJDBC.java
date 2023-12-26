package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement(
                    "SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE seller.Id = ?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){  // TESTANDO SE VEIO ALGUM RESULTADO DA CONSULTA
                Department dep = new Department();
                dep.setId(resultSet.getInt("DepartmentId"));
                dep.setName(resultSet.getString("DepName"));

                Seller obj = new Seller();
                obj.setId(resultSet.getInt("Id"));
                obj.setName(resultSet.getString("Name"));
                obj.setEmail(resultSet.getString("Email"));
                obj.setBaseSalary(resultSet.getDouble("BaseSalary"));
                obj.setBirthDate(resultSet.getDate("BirthDate"));
                obj.setDepartment(dep);
                return obj;
            }
            return null;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
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
