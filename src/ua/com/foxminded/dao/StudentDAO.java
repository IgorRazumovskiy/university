package ua.com.foxminded.dao;

import java.util.List;

import ua.com.foxminded.domain.Student;

public interface StudentDAO extends GenericDAO<Student, Integer> {

    public Student create(Student student);
    
    public Student update(Student student);

    public Student findOne(Integer id);

    public List<Student> findAll();

    public Student delete(Integer id);
    
    public List<Student> findStudentsByGroup(Integer id);
    
}
