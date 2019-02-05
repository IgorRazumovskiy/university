package ua.com.foxminded.dao;

import java.util.List;

import ua.com.foxminded.domain.Course;

public interface CourseDAO extends GenericDAO<Course, Integer> {

    List<Course> findCoursesByChair(Integer id);

    List<Course> findCoursesByTeacher(Integer id);

}
