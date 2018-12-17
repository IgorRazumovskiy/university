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

    public synchronized static DAOProperties getInstance() throws DAOException {
        if (instance == null) {
            try (FileInputStream fis = new FileInputStream("resources/dao.properties")) {
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
