package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.com.foxminded.domain.Student;

public class StudentDAOImpl implements StudentDAO {
    public ConnectionFactory connectionFactory = new ConnectionFactory();

    public Student create(Student student) throws DAOException {
        String sql = "INSERT INTO student (name) VALUES (?);";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, student.getName());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            student.setId(rs.getInt("id"));
        } catch (SQLException e) {
            throw new DAOException("Cannot create student", e);
        }

        return student;
    }

    public Student update(Student student) throws DAOException {
        String sql = "UPDATE student SET name = ? WHERE id = ?;";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot update student", e);
        }

        return student;
    }

    public Student findOne(Integer id) throws DAOException {
        String sql = "SELECT * FROM student WHERE id = ?;";
        Student student = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find student", e);
        }

        return student;
    }

    public List<Student> findAll() throws DAOException {
        String sql = "SELECT * FROM student;";
        List<Student> studentList = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                studentList.add(student);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find all students", e);
        }

        return studentList;
    }

    public Student delete(Integer id) throws DAOException {
        String sql = "DELETE FROM student WHERE id = ?;";
        Student student = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                student = new Student();
                student.setId(id);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot delete student", e);
        }

        return student;
    }

    public List<Student> findStudentsByGroup(Integer group_id) throws DAOException {
        String sql = "SELECT * FROM student WHERE group_id = ?;";
        List<Student> studentList = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, group_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                studentList.add(student);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find students in group", e);
        }

        return studentList;
    }

}
