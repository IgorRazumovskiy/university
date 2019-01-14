package ua.com.foxminded.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.com.foxminded.dao.ChairDAO;
import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.dao.CourseDAO;
import ua.com.foxminded.dao.DAOException;
import ua.com.foxminded.dao.TeacherDAO;
import ua.com.foxminded.domain.Chair;

public class ChairDAOImpl implements ChairDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();
    private final TeacherDAO teacherDAO = new TeacherDAOImpl();
    private final CourseDAO courseDAO = new CourseDAOImpl();
    private static final Logger log = LogManager.getLogger(ChairDAOImpl.class.getName());

    public Chair create(Chair chair) throws DAOException {
        log.info("Creating new Chair with id = " + chair.getId());
        String sql = "INSERT INTO chair (name) VALUES (?)";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, chair.getName());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            chair.setId(rs.getInt("id"));
            log.info("Chair with id = " + rs.getInt("id") + " created!");
        } catch (SQLException e) {
            log.error("Cannot create chair", e);
            throw new DAOException("Cannot create chair", e);
        }
        return chair;
    }

    public Chair update(Chair chair) throws DAOException {
        log.info("Updating Chair with id = " + chair.getId());
        String sql = "UPDATE chair SET name = ? WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, chair.getName());
            statement.setInt(2, chair.getId());
            statement.executeUpdate();
            log.info("Chair with id = " + chair.getId() + " updated!");
        } catch (SQLException e) {
            log.error("Cannot update chair", e);
            throw new DAOException("Cannot update chair", e);
        }
        return chair;
    }

    public Chair findOne(Integer id) throws DAOException {
        log.info("Finding Chair with id = " + id);
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
            chair.setTeachers(teacherDAO.findTeachersByChair(id));
            chair.setCourses(courseDAO.findCoursesByChair(id));
            log.info("Chair with id = " + id + " finded!");
        } catch (SQLException e) {
            log.error("Cannot find chair", e);
            throw new DAOException("Cannot find chair", e);
        }
        return chair;
    }

    public List<Chair> findAll() throws DAOException {
        log.info("Finding all chairs");
        String sql = "SELECT * FROM chair";
        List<Chair> chairList = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Chair chair = new Chair();
                chair.setId(rs.getInt("id"));
                chair.setName(rs.getString("name"));
                chair.setTeachers(teacherDAO.findTeachersByChair(rs.getInt("id")));
                chair.setCourses(courseDAO.findCoursesByChair(rs.getInt("id")));
                chairList.add(chair);
            }
            log.info("All chairs finded!");
        } catch (SQLException e) {
            log.error("Cannot find all chairs", e);
            throw new DAOException("Cannot find all chairs", e);
        }
        return chairList;
    }

    public Chair delete(Integer id) throws DAOException {
        log.info("Deleting Chair with id = " + id);
        String sql = "DELETE FROM chair WHERE id = ?";
        Chair chair = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                chair = new Chair();
                chair.setId(id);
                log.info("Chair with id = " + id + " deleted!");
            }
        } catch (SQLException e) {
            log.error("Cannot delete chair", e);
            throw new DAOException("Cannot delete chair", e);
        }
        return chair;
    }

    public List<Chair> findChairsByFaculty(Integer facultyId) throws DAOException {
        log.info("Finding chairs with facultyId = " + facultyId);
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
                log.info("All chairs with facultyId = " + facultyId + " finded!");
            }
        } catch (SQLException e) {
            log.error("Cannot find chair", e);
            throw new DAOException("Cannot find chairs in faculty", e);
        }
        return chairList;
    }

}