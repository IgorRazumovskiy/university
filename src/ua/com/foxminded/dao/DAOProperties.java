package ua.com.foxminded.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DAOProperties {
    private static DAOProperties instance;
    private String url;
    private String user;
    private String password;

    private DAOProperties() throws DAOException {
        try (FileInputStream fis = new FileInputStream("resources/dao.properties")) {
            Properties prop = new Properties();
            prop.load(fis);
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
        } catch (IOException e) {
            throw new DAOException("Cannot find file properties", e);
        }
    }

    public synchronized static DAOProperties getInstance() throws DAOException {
        if (instance == null)
            instance = new DAOProperties();
        return instance;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

}
