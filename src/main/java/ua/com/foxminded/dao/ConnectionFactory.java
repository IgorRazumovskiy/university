package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConnectionFactory {

    public Connection getConnection() {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
            DataSource dataSource = (DataSource) context.getBean(javax.sql.DataSource.class, "dataSource");
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DAOException("Cannot get connection", e);
        }
    }

}
