package ua.com.foxminded.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DAOProperties {
    private static DAOProperties instance;
    private String url;
    private String user;
    private String password;

    private DAOProperties() {

    }

    public synchronized static DAOProperties getInstance() {
        if (instance == null) {
            try (FileInputStream fis = new FileInputStream("dao.properties")) {
                Properties prop = new Properties();
                prop.load(fis);
                instance = new DAOProperties();
                instance.url = prop.getProperty("url");
                instance.user = prop.getProperty("user");
                instance.password = prop.getProperty("password");
            } catch (IOException e) {
                throw new DAOException("Cannot find file properties", e);
            }
        }
        return instance;
    }

    String getUrl() {
        return url;
    }

    String getUser() {
        return user;
    }

    String getPassword() {
        return password;
    }

}
