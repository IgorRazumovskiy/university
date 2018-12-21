package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory  {
    public DAOProperties properties = DAOProperties.getInstance();

    public Connection getConnection() throws DAOException {
        try {
            return DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPassword());
        } catch (SQLException e) {
            throw new DAOException("Cannot get connection", e);
        }
    }

}
