package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection connection;
    public DepartmentDaoJDBC(Connection connection){
        this.connection = connection;
    }
    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement(
                    "INSERT INTO department " +
                    "(Name) " +
                    "VALUES " +
                    "(?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1,obj.getName());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            }else {
                throw new DbException("Erro inesperado, nenhuma linha afetada");
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement(
                    "UPDATE department " +
                    "SET Name = ?" +
                    "WHERE Id = ?",Statement.RETURN_GENERATED_KEYS);

            st.setString(1,obj.getName());
            st.setInt(2,obj.getId());
            st.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        return null;
    }

    @Override
    public List<Department> findAll() {
        return null;
    }
}
