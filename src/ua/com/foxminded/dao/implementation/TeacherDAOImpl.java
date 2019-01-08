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
import ua.com.foxminded.dao.TeacherDAO;
import ua.com.foxminded.domain.Teacher;

public class TeacherDAOImpl implements TeacherDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();
    private final CourseDAO courseDAO = new CourseDAOImpl();

    public Teacher create(Teacher teacher) throws DAOException {
        String sql = "INSERT INTO teacher (name) VALUES (?)";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, teacher.getName());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            teacher.setId(rs.getInt("id"));
        } catch (SQLException e) {
            throw new DAOException("Cannot create teacher", e);
        }
        return teacher;
    }

    public Teacher update(Teacher teacher) throws DAOException {
        String sql = "UPDATE teacher SET name = ? WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, teacher.getName());
            statement.setInt(2, teacher.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot update teacher", e);
        }
        return teacher;
    }

    public Teacher findOne(Integer id) throws DAOException {
        String sql = "SELECT * FROM teacher WHERE id = ?";
        Teacher teacher = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            teacher = new Teacher();
            while (rs.next()) {                
                teacher.setId(rs.getInt("id"));
                teacher.setName(rs.getString("name"));              
            }
            teacher.setCourses(courseDAO.findCoursesByTeacher(rs.getInt("id")));
        } catch (SQLException e) {
            throw new DAOException("Cannot find teacher", e);
        }
        return teacher;
    }

    public List<Teacher> findAll() throws DAOException {
        String sql = "SELECT * FROM teacher";
        List<Teacher> teacherList = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id"));
                teacher.setName(rs.getString("name"));
                teacher.setCourses(courseDAO.findCoursesByTeacher(rs.getInt("id")));
                teacherList.add(teacher);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find all teachers", e);
        }
        return teacherList;
    }

    public Teacher delete(Integer id) throws DAOException {
        String sql = "DELETE FROM teacher WHERE id = ?";
        Teacher teacher = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                teacher = new Teacher();
                teacher.setId(id);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot delete teacher", e);
        }
        return teacher;
    }

    public List<Teacher> findTeachersByChair(Integer chairId) throws DAOException {
        String sql = "SELECT * FROM teacher WHERE chair_id = ?";
        List<Teacher> teacherList = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, chairId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id"));
                teacher.setName(rs.getString("name"));
                teacherList.add(teacher);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find teachers in chair", e);
        }
        return teacherList;
    }

}
