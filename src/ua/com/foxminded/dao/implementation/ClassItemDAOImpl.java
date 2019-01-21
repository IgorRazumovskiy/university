package ua.com.foxminded.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ua.com.foxminded.dao.ClassItemDAO;
import ua.com.foxminded.dao.ClassroomDAO;
import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.dao.CourseDAO;
import ua.com.foxminded.dao.DAOException;
import ua.com.foxminded.dao.GroupDAO;
import ua.com.foxminded.dao.TeacherDAO;
import ua.com.foxminded.domain.ClassItem;

public class ClassItemDAOImpl implements ClassItemDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();
    private final TeacherDAO teacherDAO = new TeacherDAOImpl();
    private final GroupDAO groupDAO = new GroupDAOImpl();
    private final ClassroomDAO classroomDAO = new ClassroomDAOImpl();
    private final CourseDAO courseDAO = new CourseDAOImpl();

    public ClassItem create(ClassItem classItem) {
        String sql = "INSERT INTO lesson (teacher_id, group_id, classroom_id, course_id, datetime) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, classItem.getTeacher().getId());
            statement.setInt(2, classItem.getGroup().getId());
            statement.setInt(3, classItem.getClassroom().getId());
            statement.setInt(4, classItem.getCourse().getId());
            statement.setObject(5, classItem.getDateTime());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            classItem.setId(rs.getInt("id"));
        } catch (SQLException e) {
            throw new DAOException("Cannot create classItem", e);
        }
        return classItem;
    }

    public ClassItem update(ClassItem classItem) {
        String sql = "UPDATE lesson SET teacher_id = ?, group_id = ?, classroom_id = ?, course_id = ?, datetime = ? WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, classItem.getTeacher().getId());
            statement.setInt(2, classItem.getGroup().getId());
            statement.setInt(3, classItem.getClassroom().getId());
            statement.setInt(4, classItem.getCourse().getId());
            statement.setObject(5, classItem.getDateTime());
            statement.setInt(6, classItem.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot update classItem", e);
        }
        return classItem;
    }

    public ClassItem findOne(Integer id) {
        String sql = "SELECT * FROM lesson WHERE id = ?";
        ClassItem classItem = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                classItem = new ClassItem();
                classItem.setId(rs.getInt("id"));
                classItem.setTeacher(teacherDAO.findOne(rs.getInt("teacher_id")));
                classItem.setGroup(groupDAO.findOne(rs.getInt("group_id")));
                classItem.setClassroom(classroomDAO.findOne(rs.getInt("classroom_id")));
                classItem.setCourse(courseDAO.findOne(rs.getInt("course_id")));
                classItem.setDateTime(rs.getObject("datetime", LocalDateTime.class));
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find classItem", e);
        }
        return classItem;
    }

    public List<ClassItem> findAll() {
        String sql = "SELECT * FROM lesson";
        List<ClassItem> classItemList = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ClassItem classItem = new ClassItem();
                classItem.setId(rs.getInt("id"));
                classItem.setTeacher(teacherDAO.findOne(rs.getInt("teacher_id")));
                classItem.setGroup(groupDAO.findOne(rs.getInt("group_id")));
                classItem.setClassroom(classroomDAO.findOne(rs.getInt("classroom_id")));
                classItem.setCourse(courseDAO.findOne(rs.getInt("course_id")));
                classItem.setDateTime(rs.getObject("datetime", LocalDateTime.class));
                classItemList.add(classItem);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find all classItems", e);
        }
        return classItemList;
    }

    public ClassItem delete(Integer id) {
        String sql = "DELETE FROM lesson WHERE id = ?";
        ClassItem classItem = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                classItem = new ClassItem();
                classItem.setId(id);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot delete classItem", e);
        }
        return classItem;
    }

}
