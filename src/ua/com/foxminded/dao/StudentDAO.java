package ua.com.foxminded.dao;

import java.util.List;

import ua.com.foxminded.domain.Student;

public interface StudentDAO extends GenericDAO<Student, Integer> {

    public List<Student> findStudentsByGroup(Integer id);
    
}
