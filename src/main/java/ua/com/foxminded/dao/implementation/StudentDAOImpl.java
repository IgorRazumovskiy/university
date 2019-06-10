package ua.com.foxminded.dao.implementation;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import ua.com.foxminded.dao.HibernateUtil;
import ua.com.foxminded.dao.StudentDAO;
import ua.com.foxminded.domain.Student;

public class StudentDAOImpl implements StudentDAO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Transactional
    public Student create(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.save(student);
        return student;
    }

    @Transactional
    public Student update(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.update(student);
        return student;
    }

    @Transactional
    public Student findOne(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, id);
        return student;
    }

    @Transactional
    public List<Student> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Student> query = session.createQuery("from Student", Student.class);
        List<Student> studentList = query.getResultList();
        return studentList;
    }

    @Transactional
    public Student delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, id);
        session.delete(student);
        return student;
    }

    @Transactional
    public List<Student> findStudentsByGroup(Integer groupId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Student> query = session.createQuery("from Student where groupId=:studentGroupId", Student.class);
        query.setParameter("studentGroupId", groupId);
        List<Student> studentList = query.getResultList();
        return studentList;
    }

}
