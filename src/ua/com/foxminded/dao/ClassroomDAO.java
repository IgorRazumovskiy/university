package ua.com.foxminded.dao;

import java.util.List;

import ua.com.foxminded.domain.Classroom;

public interface ClassroomDAO extends GenericDAO<Classroom, Integer> {

    List<Classroom> findClassroomsByFaculty(Integer id);

}
