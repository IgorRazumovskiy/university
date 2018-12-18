package ua.com.foxminded.dao;

import java.util.List;

import ua.com.foxminded.domain.Student;

public interface StudentDAO {

    public Student create(Student student) throws DAOException;
    
    public Student update(Student student) throws DAOException;

    public Student findOne(Integer id) throws DAOException;

    public List<Student> findAll() throws DAOException;

    public Student delete(Integer id) throws DAOException;
    
    public List<Student> findStudentsByGroup(Integer id) throws DAOException;
    
}
