package db;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    public static Connection connection = null;
    public static Connection getConnection(){

        if (connection == null){
            Properties properties = loadProperties();
            String url = properties.getProperty("dburl");
            try {
                connection = DriverManager.getConnection(url,properties);
                return connection;
            }catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
       return connection;
    }
    private static Properties loadProperties(){
        try (FileInputStream fs = new FileInputStream("src/db.properties")){
            Properties properties = new Properties();
            properties.load(fs);
            return properties;
        } catch (IOException e){
            throw new DbException(e.getMessage());
        }
    }
    public static void closeConnection(){
        try {
            connection.close();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
}
