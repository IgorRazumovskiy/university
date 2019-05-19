package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {

    public Connection getConnection() {
        try {
            DataSource dataSource = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/university");
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DAOException("Cannot get connection", e);
        } catch (NamingException e) {
            throw new DAOException("Cannot get connection", e);
        }
    }
    
}
