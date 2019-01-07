package ua.com.foxminded.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.com.foxminded.dao.ChairDAO;
import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.dao.DAOException;
import ua.com.foxminded.domain.Chair;

public class ChairDAOImpl implements ChairDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();
    private TeacherDAOImpl teacher = new TeacherDAOImpl();
    private CourseDAOImpl course = new CourseDAOImpl();

    public Chair create(Chair chair) throws DAOException {
        String sql = "INSERT INTO chair (name) VALUES (?)";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, chair.getName());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            chair.setId(rs.getInt("id"));
        } catch (SQLException e) {
            throw new DAOException("Cannot create chair", e);
        }
        return chair;
    }

    public Chair update(Chair chair) throws DAOException {
        String sql = "UPDATE chair SET name = ? WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, chair.getName());
            statement.setInt(2, chair.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot update chair", e);
        }
        return chair;
    }

    public Chair findOne(Integer id) throws DAOException {
        String sql = "SELECT * FROM chair WHERE id = ?";
        Chair chair = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            chair = new Chair();
            while (rs.next()) {
                chair.setId(rs.getInt("id"));
                chair.setName(rs.getString("name"));
            }
            chair.setTeachers(teacher.findTeachersByChair(id));
            chair.setCourses(course.findCoursesByChair(id));
        } catch (SQLException e) {
            throw new DAOException("Cannot find chair", e);
        }
        return chair;
    }

    public List<Chair> findAll() throws DAOException {
        String sql = "SELECT * FROM chair";
        List<Chair> chairList = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Chair chair = new Chair();
                chair.setId(rs.getInt("id"));
                chair.setName(rs.getString("name"));
                chair.setTeachers(teacher.findTeachersByChair(rs.getInt("id")));
                chair.setCourses(course.findCoursesByChair(rs.getInt("id")));
                chairList.add(chair);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find all chairs", e);
        }
        return chairList;
    }

    public Chair delete(Integer id) throws DAOException {
        String sql = "DELETE FROM chair WHERE id = ?";
        Chair chair = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                chair = new Chair();
                chair.setId(id);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot delete chair", e);
        }
        return chair;
    }

    public List<Chair> findChairsByFaculty(Integer facultyId) throws DAOException {
        String sql = "SELECT * FROM chair WHERE faculty_id = ?";
        List<Chair> chairList = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, facultyId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Chair chair = new Chair();
                chair.setId(rs.getInt("id"));
                chair.setName(rs.getString("name"));
                chairList.add(chair);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find chairs in faculty", e);
        }
        return chairList;
    }

}
