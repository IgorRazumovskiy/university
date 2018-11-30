package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.com.foxminded.domain.Student;

public class StudentDAO {

    public Student create(Student student) throws DAOException {
        String sql = "INSERT INTO student (name) VALUES (?);";
        String name = student.getName();
        Student studentCreated = null;
        try (Connection connection = DAOPostgreSQLConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, name);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            studentCreated = new Student(rs.getString("name"));
            studentCreated.setId(rs.getInt("id"));
        } catch (SQLException e) {
            throw new DAOException("Cannot create student", e);
        }

        return studentCreated;
    }

    public Student update(Student student, Integer group_id) throws DAOException {
        String sql = "UPDATE student SET name = ?, group_id = ? WHERE id = ?;";
        String name = student.getName();
        Integer id = student.getId();
        Student studentUpdated = null;
        try (Connection connection = DAOPostgreSQLConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, name);
            statement.setInt(2, group_id);
            statement.setInt(3, id);
            if (statement.executeUpdate() != 0) {
                studentUpdated = new Student(name);
                studentUpdated.setId(id);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot update student", e);
        }

        return studentUpdated;
    }

    public Student findOne(Integer id) throws DAOException {
        String sql = "SELECT * FROM student WHERE id = ?;";
        Student student = null;
        try (Connection connection = DAOPostgreSQLConnection.getConnection();
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
        try (Connection connection = DAOPostgreSQLConnection.getConnection();
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
        try (Connection connection = DAOPostgreSQLConnection.getConnection();
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

}
