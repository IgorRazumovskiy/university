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
import ua.com.foxminded.dao.GroupDAO;
import ua.com.foxminded.dao.StudentDAO;
import ua.com.foxminded.domain.Group;

public class GroupDAOImpl implements GroupDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();
    private final StudentDAO studentDAO = new StudentDAOImpl();

    public Group create(Group group) {
        String sql = "INSERT INTO groups (name) VALUES (?)";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, group.getName());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            group.setId(rs.getInt("id"));
        } catch (SQLException e) {
            throw new DAOException("Cannot create group", e);
        }
        return group;
    }

    public Group update(Group group) {
        String sql = "UPDATE groups SET name = ? WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot update group", e);
        }
        return group;
    }

    public Group findOne(Integer id) {
        String sql = "SELECT * FROM groups WHERE id = ?";
        Group group = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            group = new Group();
            while (rs.next()) {
                group.setId(rs.getInt("id"));
                group.setName(rs.getString("name"));
            }
            group.setStudents(studentDAO.findStudentsByGroup(id));
        } catch (SQLException e) {
            throw new DAOException("Cannot find group", e);
        }
        return group;
    }

    public List<Group> findAll() {
        String sql = "SELECT * FROM groups";
        List<Group> groupList = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setName(rs.getString("name"));
                group.setStudents(studentDAO.findStudentsByGroup(rs.getInt("id")));
                groupList.add(group);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find all groups", e);
        }
        return groupList;
    }

    public Group delete(Integer id) {
        String sql = "DELETE FROM groups WHERE id = ?";
        Group group = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                group = new Group();
                group.setId(id);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot delete group", e);
        }
        return group;
    }

    public List<Group> findGroupsByFaculty(Integer facultyId) {
        String sql = "SELECT * FROM groups WHERE faculty_id = ?";
        List<Group> groupList = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, facultyId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setName(rs.getString("name"));
                groupList.add(group);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find groups in faculty", e);
        }
        return groupList;
    }

}
