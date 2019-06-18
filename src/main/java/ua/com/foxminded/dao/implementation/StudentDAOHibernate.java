package ua.com.foxminded.dao.implementation;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.com.foxminded.dao.HibernateUtil;
import ua.com.foxminded.dao.StudentDAO;
import ua.com.foxminded.domain.Student;

public class StudentDAOHibernate implements StudentDAO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Student create(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        return student;
    }

    public Student update(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
        return student;
    }

    public Student findOne(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Student student = session.get(Student.class, id);
        session.getTransaction().commit();
        return student;
    }

    public List<Student> findAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Student> query = session.createQuery("from Student", Student.class);
        List<Student> studentList = query.getResultList();
        session.getTransaction().commit();
        return studentList;
    }

    public Student delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Student student = session.get(Student.class, id);
        session.delete(student);
        session.getTransaction().commit();
        return student;
    }

    public List<Student> findStudentsByGroup(Integer groupId) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Student> query = session.createQuery("from Student where group_id = : studentGroupId", Student.class);
        query.setParameter("studentGroupId", groupId);
        List<Student> studentList = query.getResultList();
        session.getTransaction().commit();
        return studentList;
    }
}
