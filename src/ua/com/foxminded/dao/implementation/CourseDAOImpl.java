package ua.com.foxminded.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.dao.CourseDAO;
import ua.com.foxminded.dao.DAOException;
import ua.com.foxminded.domain.Course;

public class CourseDAOImpl implements CourseDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    public Course create(Course course) {
        String sql = "INSERT INTO course (name) VALUES (?)";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, course.getName());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            course.setId(rs.getInt("id"));
        } catch (SQLException e) {
            throw new DAOException("Cannot create course", e);
        }
        return course;
    }

    public Course update(Course course) {
        String sql = "UPDATE course SET name = ? WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, course.getName());
            statement.setInt(2, course.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot update course", e);
        }
        return course;
    }

    public Course findOne(Integer id) {
        String sql = "SELECT * FROM course WHERE id = ?";
        Course course = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find course", e);
        }
        return course;
    }

    public List<Course> findAll() {
        String sql = "SELECT * FROM course";
        List<Course> courseList = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                courseList.add(course);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find all courses", e);
        }
        return courseList;
    }

    public Course delete(Integer id) {
        String sql = "DELETE FROM course WHERE id = ?";
        Course course = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                course = new Course();
                course.setId(id);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot delete course", e);
        }
        return course;
    }
    
    public List<Course> findCoursesByChair(Integer chairId) { 
        String sql = "SELECT * FROM course WHERE chair_id = ?";
        List<Course> courseList = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, chairId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                courseList.add(course);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find courses in chair", e);
        }
        return courseList;
    }

    public List<Course> findCoursesByTeacher(Integer teacherId) { 
        String sql = "SELECT id, name FROM course c INNER JOIN course_teacher ct ON c.id = ct.course_id WHERE teacher_id = ?";
        List<Course> courseList = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, teacherId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                courseList.add(course);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find courses by teacher", e);
        }
        return courseList;
    }

}
