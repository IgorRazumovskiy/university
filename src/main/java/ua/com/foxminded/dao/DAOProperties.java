package ua.com.foxminded.dao;

import java.io.IOException;
import java.io.InputStream;
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
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try (InputStream stream = loader.getResourceAsStream("dao.properties")) {
                Properties prop = new Properties();
                prop.load(stream);
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
