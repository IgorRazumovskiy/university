package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public DAOProperties properties;

    public DBConnection() {

    }

    public DAOProperties getProperties() {
        return properties;
    }

    public void setProperties(DAOProperties properties) {
        this.properties = properties;
    }

    public Connection getConnection() throws DAOException {
        if (properties == null) {
            setProperties(new DAOProperties());
            properties.readProperties();
        }
        try {
            return DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPassword());
        } catch (SQLException e) {
            throw new DAOException("Cannot get connection", e);
        }
    }

}
