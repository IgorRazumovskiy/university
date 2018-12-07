package ua.com.foxminded.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DAOProperties {
    private String url;
    private String user;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void readProperties() throws DAOException {
        try (FileInputStream fis = new FileInputStream("resources/dao.properties");) {
            Properties prop = new Properties();
            prop.load(fis);
            setUrl(prop.getProperty("url"));
            setUser(prop.getProperty("user"));
            setPassword(prop.getProperty("password"));
        } catch (IOException e) {
            throw new DAOException("Cannot find file properties", e);
        }
    }

}
