package ua.com.foxminded.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        sessionFactory = (SessionFactory) context
                .getBean(org.springframework.orm.hibernate5.LocalSessionFactoryBean.class, "sessionFactory");
    }

    public static SessionFactory getSessionFactory() throws HibernateException {
        return sessionFactory;
    }

}
