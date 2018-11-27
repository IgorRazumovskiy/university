package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ua.com.foxminded.domain.Student;

public class StudentDAO {

    public Student create(String name, Integer group_id) throws DAOException {
        String sql = "INSERT INTO public.student (name, group_id) VALUES (?, ?);";
        Student student = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DAOPostgreSQLConnection.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setInt(2, group_id);
            statement.execute();
            rs = statement.getGeneratedKeys();
            rs.next();
            student = new Student(rs.getString("name"));
            student.setId(rs.getInt("id"));
        } catch (SQLException e) {
            throw new DAOException("Cannot create student", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Cannot close statement", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Cannot close connection", e);
            }
        }
        return student;
    }

    public Student update(Integer id, String name, Integer group_id) throws DAOException {
        String sql = "UPDATE public.student SET name = ?, group_id = ? WHERE id = ?;";
        Student student = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DAOPostgreSQLConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, group_id);
            statement.setInt(3, id);
            if (statement.executeUpdate() != 0) {
                student = new Student(name);
                student.setId(id);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot update student", e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Cannot close statement", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Cannot close connection", e);
            }
        }
        return student;
    }

    public Student read(String name) throws DAOException {
        String sql = "SELECT * FROM public.student WHERE name = ?;";
        Student student = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DAOPostgreSQLConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            rs = statement.executeQuery();
            while (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot read student", e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Cannot close statement", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Cannot close connection", e);
            }
        }
        return student;
    }
    
    public Student delete(Integer id) throws DAOException {
        String sql = "DELETE FROM public.student WHERE id = ?;";
        Student student = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DAOPostgreSQLConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                student = new Student();
                student.setId(id);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot delete student", e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Cannot close statement", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Cannot close connection", e);
            }
        }
        return student;
    }

}
