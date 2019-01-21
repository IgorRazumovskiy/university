package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory  {
    private DAOProperties properties = DAOProperties.getInstance();

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPassword());
        } catch (SQLException e) {
            throw new DAOException("Cannot get connection", e);
        }
    }

}
