package ua.com.foxminded.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.dao.DAOException;
import ua.com.foxminded.dao.StudentDAO;
import ua.com.foxminded.domain.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {
    @Autowired
    private DataSource dataSource;
    
    public Student create(Student student) {
        String sql = "INSERT INTO student (name) VALUES (?)";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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

    public Student update(Student student) {
        String sql = "UPDATE student SET name = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot update student", e);
        }
        return student;
    }

    public Student findOne(Integer id) {
        String sql = "SELECT * FROM student WHERE id = ?";
        Student student = null;
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
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

    public List<Student> findAll() {
        String sql = "SELECT * FROM student";
        List<Student> studentList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
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

    public Student delete(Integer id) {
        String sql = "DELETE FROM student WHERE id = ?";
        Student student = null;
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
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

    public List<Student> findStudentsByGroup(Integer groupId) {
        String sql = "SELECT * FROM student WHERE group_id = ?";
        List<Student> studentList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, groupId);
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
