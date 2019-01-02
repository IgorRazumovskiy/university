package ua.com.foxminded.dao;

import java.util.List;

import ua.com.foxminded.domain.Course;

public interface CourseDAO extends GenericDAO<Course, Integer> {

    public List<Course> findCoursesByChair(Integer id);

    public List<Course> findCoursesByTeacher(Integer id);

}
