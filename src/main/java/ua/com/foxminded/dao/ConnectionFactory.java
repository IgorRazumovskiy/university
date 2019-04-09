package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {
    private DataSource dataSource;

    public Connection getConnection() {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            dataSource = (DataSource) envCtx.lookup("jdbc/university");
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DAOException("Cannot get connection", e);
        } catch (NamingException e) {
            throw new DAOException("Cannot get connection", e);
        }
    }

}
