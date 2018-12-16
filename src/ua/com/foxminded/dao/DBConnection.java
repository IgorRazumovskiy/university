package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static DAOProperties props;

    public Connection getConnection() throws DAOException {
        if (props == null) {
            props = new DAOProperties();
            props.readProperties();
        }
        try {
            return DriverManager.getConnection(props.getUrl(), props.getUser(), props.getPassword());
        } catch (SQLException e) {
            throw new DAOException("Cannot get connection", e);
        }
    }

}
