package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.web.context.ContextLoader;

public class ConnectionFactory {

    public Connection getConnection() {
        try {
            DataSource dataSource = (DataSource) ContextLoader.getCurrentWebApplicationContext()
                    .getBean(javax.sql.DataSource.class, "dataSource");
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DAOException("Cannot get connection", e);
        }
    }

}
