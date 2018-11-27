package ua.com.foxminded.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.com.foxminded.domain.Student;

public class StudentDAOTest {
    StudentDAO studentDAO = new StudentDAO();
    
    @Test
    public void testCreateStudent() throws DAOException {
        Student expected = new Student("Новый");
        Student actual = studentDAO.create("Новый", 3);
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void testUpdateStudentWhenStudentInDB() throws DAOException {
        Student expected = new Student("Аннабель");
        expected.setId(30);
        Student actual = studentDAO.update(30, "Аннабель", 2);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected, actual);
    }

    @Test
    public void testUpdateStudentWhenNotStudentInDB() throws DAOException {
        Student actual = studentDAO.update(135, "Тест", 2);
        assertNull(actual);
    }

    @Test
    public void testReadStudentWhenStudentInDB() throws DAOException {
        Student expected = new Student("Джоан");
        Student actual = studentDAO.read("Джоан");
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void testReadStudentWhenNotStudentInDB() throws DAOException {
        Student actual = studentDAO.read("Родригес");
        assertNull(actual);
    }

    @Test
    public void testDeleteStudentWhenStudentInDB() throws DAOException {
        Student expected = new Student();
        expected.setId(30);
        Student actual = studentDAO.delete(30);
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteStudentWhenNotStudentInDB() throws DAOException {
        Student actual = studentDAO.delete(120);
        assertNull(actual);
    }
}
