package ua.com.foxminded.dao.implementation;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import ua.com.foxminded.dao.GroupDAO;
import ua.com.foxminded.dao.HibernateUtil;
import ua.com.foxminded.domain.Group;

public class GroupDAOHibernate implements GroupDAO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Group create(Group group) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(group);
        session.getTransaction().commit();
        return group;
    }

    public Group update(Group group) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(group);
        session.getTransaction().commit();
        return group;
    }

    public Group findOne(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Group group = session.get(Group.class, id);
        session.getTransaction().commit();
        return group;
    }

    public List<Group> findAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Group> query = session.createQuery("from Group", Group.class);
        List<Group> groupList = query.getResultList();
        session.getTransaction().commit();
        return groupList;
    }

    public Group delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Group group = session.get(Group.class, id);
        session.delete(group);
        session.getTransaction().commit();
        return group;
    }

    public List<Group> findGroupsByFaculty(Integer facultyId) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Group> query = session.createQuery("from Group where facultyId=:groupfacultyId", Group.class);
        query.setParameter("groupfacultyId", facultyId);
        List<Group> groupList = query.getResultList();
        session.getTransaction().commit();
        return groupList;
    }

}
