package ua.com.foxminded.dao;

import java.util.List;

import ua.com.foxminded.domain.Teacher;

public interface TeacherDAO extends GenericDAO<Teacher, Integer> {

    List<Teacher> findTeachersByChair(Integer id);

}
