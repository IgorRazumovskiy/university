package ua.com.foxminded.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.dao.DAOException;
import ua.com.foxminded.dao.FacultyDAO;
import ua.com.foxminded.domain.Faculty;

public class FacultyDAOImpl implements FacultyDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    public Faculty create(Faculty faculty) throws DAOException {
        String sql = "INSERT INTO faculty (name) VALUES (?)";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, faculty.getName());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            faculty.setId(rs.getInt("id"));
        } catch (SQLException e) {
            throw new DAOException("Cannot create faculty", e);
        }
        return faculty;
    }

    public Faculty update(Faculty faculty) throws DAOException {
        String sql = "UPDATE faculty SET name = ? WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, faculty.getName());
            statement.setInt(2, faculty.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot update faculty", e);
        }
        return faculty;
    }

    public Faculty findOne(Integer id) throws DAOException {
        String sql = "SELECT * FROM faculty WHERE id = ?";
        Faculty faculty = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                faculty = new Faculty();
                faculty.setId(rs.getInt("id"));
                faculty.setName(rs.getString("name"));
                faculty.setGroups(new GroupDAOImpl().findGroupsByFaculty(rs.getInt("id")));
                faculty.setClassrooms(new ClassroomDAOImpl().findClassroomsByFaculty(rs.getInt("id")));
                faculty.setChairs(new ChairDAOImpl().findChairsByFaculty(rs.getInt("id")));
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find faculty", e);
        }
        return faculty;
    }

    public List<Faculty> findAll() throws DAOException {
        String sql = "SELECT * FROM faculty";
        List<Faculty> facultyList = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Faculty faculty = new Faculty();
                faculty.setId(rs.getInt("id"));
                faculty.setName(rs.getString("name"));
                facultyList.add(faculty);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find all faculties", e);
        }
        return facultyList;
    }

    public Faculty delete(Integer id) throws DAOException {
        String sql = "DELETE FROM faculty WHERE id = ?";
        Faculty faculty = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                faculty = new Faculty();
                faculty.setId(id);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot delete faculty", e);
        }
        return faculty;
    }

}
