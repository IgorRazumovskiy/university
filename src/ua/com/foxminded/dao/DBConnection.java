package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static DAOProperties properties;

    public Connection getConnection() throws DAOException {
        if (properties == null) {
            properties = DAOProperties.getInstance();
        }
        try {
            return DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPassword());
        } catch (SQLException e) {
            throw new DAOException("Cannot get connection", e);
        }
    }

}
