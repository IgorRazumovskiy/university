package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOPostgreSQLConnection {
    private static String user = "postgres";
    private static String password = "123456";
    private static String url = "jdbc:postgresql://localhost:5432/university";
 
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
