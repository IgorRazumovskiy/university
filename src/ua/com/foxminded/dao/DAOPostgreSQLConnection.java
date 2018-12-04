package ua.com.foxminded.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOPostgreSQLConnection {

    public static Connection getConnection() throws DAOException {
        String url = "";
        String user = "";
        String password = "";
        try (FileInputStream fis = new FileInputStream("resources/dao.properties");) {
            Properties prop = new Properties();
            prop.load(fis);
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
        } catch (IOException e) {
            throw new DAOException("Cannot find file properties", e);
        }
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new DAOException("Cannot get connection", e);
        }
    }

}
