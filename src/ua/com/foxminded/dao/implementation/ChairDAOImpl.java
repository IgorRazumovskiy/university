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
        log.trace("Start create Chair");
        String sql = "INSERT INTO chair (name) VALUES (?)";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, chair.getName());
            log.trace("Execute insert statement");
            statement.execute();
            log.trace("Get result set");
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            chair.setId(rs.getInt("id"));
            log.debug("Chair " + chair + " created!");
        } catch (SQLException e) {
            log.error("Cannot create chair", e);
            throw new DAOException("Cannot create chair", e);
        }
        log.trace("Finish create Chair");
        return chair;
    }

    public Chair update(Chair chair) throws DAOException {
        log.trace("Start update Chair");
        String sql = "UPDATE chair SET name = ? WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, chair.getName());
            statement.setInt(2, chair.getId());
            log.trace("Execute update statement");
            statement.executeUpdate();
            log.debug("Chair " + chair + " updated!");
        } catch (SQLException e) {
            log.error("Cannot update chair", e);
            throw new DAOException("Cannot update chair", e);
        }
        log.trace("Finish update Chair");
        return chair;
    }

    public Chair findOne(Integer id) throws DAOException {
        log.trace("Start find Chair with id = " + id);
        String sql = "SELECT * FROM chair WHERE id = ?";
        Chair chair = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            log.trace("Get result set");
            ResultSet rs = statement.executeQuery();
            chair = new Chair();
            while (rs.next()) {
                chair.setId(rs.getInt("id"));
                chair.setName(rs.getString("name"));
            }
            chair.setTeachers(teacherDAO.findTeachersByChair(id));
            chair.setCourses(courseDAO.findCoursesByChair(id));
            log.debug("Chair with id = " + id + " finded!");
        } catch (SQLException e) {
            log.error("Cannot find chair", e);
            throw new DAOException("Cannot find chair", e);
        }
        log.trace("Finish find Chair with id = " + id);
        return chair;
    }

    public List<Chair> findAll() throws DAOException {
        log.trace("Start find all chairs");
        String sql = "SELECT * FROM chair";
        List<Chair> chairList = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Get result set");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Chair chair = new Chair();
                chair.setId(rs.getInt("id"));
                chair.setName(rs.getString("name"));
                chair.setTeachers(teacherDAO.findTeachersByChair(rs.getInt("id")));
                chair.setCourses(courseDAO.findCoursesByChair(rs.getInt("id")));
                chairList.add(chair);
            }
            log.debug(chairList.size() + " chairs finded!");
        } catch (SQLException e) {
            log.error("Cannot find all chairs", e);
            throw new DAOException("Cannot find all chairs", e);
        }
        log.trace("Finish find all chairs");
        return chairList;
    }

    public Chair delete(Integer id) throws DAOException {
        log.trace("Start delete Chair with id = " + id);
        String sql = "DELETE FROM chair WHERE id = ?";
        Chair chair = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            log.trace("Execute delete statement");
            if (statement.executeUpdate() != 0) {
                chair = new Chair();
                chair.setId(id);
                log.debug("Chair with id = " + id + " deleted!");
            } else {
                log.debug("Cannot find chair to delete!");
            }
        } catch (SQLException e) {
            log.error("Cannot delete chair", e);
            throw new DAOException("Cannot delete chair", e);
        }
        log.trace("Finish delete Chair with id = " + id);
        return chair;
    }

    public List<Chair> findChairsByFaculty(Integer facultyId) throws DAOException {
        log.trace("Start find chairs with facultyId = " + facultyId);
        String sql = "SELECT * FROM chair WHERE faculty_id = ?";
        List<Chair> chairList = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, facultyId);
            log.trace("Get result set");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Chair chair = new Chair();
                chair.setId(rs.getInt("id"));
                chair.setName(rs.getString("name"));
                chairList.add(chair);
                log.debug(chairList.size() + " chairs with facultyId = " + facultyId + " finded!");
            }
        } catch (SQLException e) {
            log.error("Cannot find chair", e);
            throw new DAOException("Cannot find chairs in faculty", e);
        }
        log.trace("Finish find chairs with facultyId = " + facultyId);
        return chairList;
    }

}
