package ua.com.foxminded.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.com.foxminded.dao.ClassroomDAO;
import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.dao.DAOException;
import ua.com.foxminded.domain.Classroom;

public class ClassroomDAOImpl implements ClassroomDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();
    
    public Classroom create(Classroom classroom) {
        String sql = "INSERT INTO classroom (number, capacity) VALUES (?, ?)";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, classroom.getName());
            statement.setInt(2, classroom.getCapacity());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            classroom.setId(rs.getInt("id"));
        } catch (SQLException e) {
            throw new DAOException("Cannot create classroom", e);
        }
        return classroom;
    }

    public Classroom update(Classroom classroom) {
        String sql = "UPDATE classroom SET number = ?, capacity = ? WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, classroom.getName());
            statement.setInt(2, classroom.getCapacity());
            statement.setInt(3, classroom.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot update classroom", e);
        }
        return classroom;
    }

    public Classroom findOne(Integer id) {
        String sql = "SELECT * FROM classroom WHERE id = ?";
        Classroom classroom = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                classroom = new Classroom();
                classroom.setId(rs.getInt("id"));
                classroom.setName(rs.getString("number"));
                classroom.setCapacity(rs.getInt("capacity"));
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find classroom", e);
        }
        return classroom;
    }

    public List<Classroom> findAll() {
        String sql = "SELECT * FROM classroom";
        List<Classroom> classroomList = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Classroom classroom = new Classroom();
                classroom.setId(rs.getInt("id"));
                classroom.setName(rs.getString("number"));
                classroom.setCapacity(rs.getInt("capacity"));
                classroomList.add(classroom);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find all classrooms", e);
        }
        return classroomList;
    }

    public Classroom delete(Integer id) {
        String sql = "DELETE FROM classroom WHERE id = ?";
        Classroom classroom = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                classroom = new Classroom();
                classroom.setId(id);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot delete classroom", e);
        }
        return classroom;
    }
    
    public List<Classroom> findClassroomsByFaculty(Integer facultyId) {
        String sql = "SELECT * FROM classroom WHERE faculty_id = ?";
        List<Classroom> classroomList = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, facultyId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Classroom classroom = new Classroom();
                classroom.setId(rs.getInt("id"));
                classroom.setName(rs.getString("number"));
                classroom.setCapacity(rs.getInt("capacity"));
                classroomList.add(classroom);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find classrooms in faculty", e);
        }
        return classroomList;
    }
    
}
