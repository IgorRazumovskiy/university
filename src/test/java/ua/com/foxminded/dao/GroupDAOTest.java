package ua.com.foxminded.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ua.com.foxminded.dao.DAOException;
import ua.com.foxminded.dao.implementation.*;
import ua.com.foxminded.domain.*;

public class GroupDAOTest {
    
    ClassroomDAOImpl groupDAO = new ClassroomDAOImpl();
    FacultyDAOImpl facultyDAO = new FacultyDAOImpl();
    CourseDAOImpl courseDAO = new CourseDAOImpl();
    TeacherDAOImpl teacherDAO = new TeacherDAOImpl();
    ClassItemDAOImpl classItemDAO = new ClassItemDAOImpl();
    ChairDAOImpl chairDAO = new ChairDAOImpl();
/*    
    @Test
    public void testCreateGroup() throws DAOException {
        Classroom expected = new Classroom();
        expected.setName("New");
        expected.setCapacity(90);
        Classroom actual = groupDAO.create(expected);
        
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void testUpdateGroupWhenInDB() throws DAOException {
        Classroom expected = new Classroom();
        expected.setName("update");
        expected.setCapacity(91);
        expected.setId(7);
        Classroom actual = groupDAO.update(expected);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected, actual);
    }

    @Test
    public void testUpdateGroupWhenNotInDB() throws DAOException {
        Classroom expected = new Classroom();
        expected.setName("updateNot");
        expected.setCapacity(92);
        expected.setId(20);
        Classroom actual = groupDAO.update(expected);
        assertNull(actual);
    }

    @Test
    public void testFindOneGroupWhenInDB() throws DAOException {
        Classroom expected = new Classroom();
        expected.setId(4);
        expected.setName("202");
        expected.setCapacity(100);        
        Classroom actual = groupDAO.findOne(4);
        assertEquals(expected.getName(), actual.getName());
        System.out.println("expected " + expected);
        System.out.println("actual " + actual);
    }

    @Test
    public void testFindOneGroupWhenNotInDB() throws DAOException {
        Classroom expected = new Classroom();
//        expected.setName("ФИН-1");
        expected.setId(15);
        Classroom actual = groupDAO.findOne(15);
        assertNull(actual);
        System.out.println("result test " + actual);
    }

    @Test
    public void testFindAllGroups() throws DAOException {

        List<Classroom> actual = groupDAO.findAll();
        for (Classroom x : actual) {
            System.out.println(x);
        }
        //assertEquals(expected, actual);
    }
    
    @Test
    public void testDeleteGroupWhenInDB() throws DAOException {
        Classroom expected = new Classroom();
        expected.setId(7);
        expected.setName("update");
        expected.setCapacity(91);
        Classroom actual = groupDAO.delete(7);
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteGroupWhenNotInDB() throws DAOException {
        Classroom expected = new Classroom();
        expected.setId(38);
        Classroom actual = groupDAO.delete(38);
        assertNull(actual);
    }
    
    @Test
    public void testFindClassroomsByFaculty() throws DAOException {

        List<Classroom> actual = groupDAO.findClassroomsByFaculty(4);
        for (Classroom x : actual) {
            System.out.println(x);
        }
        //assertEquals(expected, actual);
    }
    
    @Test
    public void testFindOneFaculty() throws DAOException {
        Faculty expected = new Faculty();
        expected.setId(2);
        expected.setName("технический");      
        Faculty actual = facultyDAO.findOne(2);
        assertEquals(expected.getName(), actual.getName());
        System.out.println(actual);
        System.out.println(actual.getGroups());
        System.out.println(actual.getClassrooms());
        
        //assertEquals(expected, actual);
    }
    
    @Test
    public void testFindCoursesByTeacher() throws DAOException {
        
        List<Course> actual = courseDAO.findCoursesByTeacher(5);
        for (Course x : actual) {
            System.out.println(x);
        }
        //assertEquals(expected, actual);
    }
*/  
    @Test
    public void testFindOneChair() throws DAOException {
        Chair expected = new Chair();
        expected.setId(2);
        expected.setName("программирования");      
        Chair actual = chairDAO.findOne(2);
        assertEquals(expected.getName(), actual.getName());
        System.out.println(actual);
        System.out.println(actual.getTeachers());
        System.out.println(actual.getCourses());
        
        //assertEquals(expected, actual);
    }
    
    @Test
    public void testFindAllChairs() throws DAOException {

        List<Chair> actual = chairDAO.findAll();
        for (Chair x : actual) {
            System.out.println(x);
            System.out.println(x.getTeachers());
            System.out.println(x.getCourses());
        }
        //assertEquals(expected, actual);
    }
    
    @Test
    public void testFindOneTeacherInDB() throws DAOException {
        Teacher expected = new Teacher();
        expected.setId(6);
        expected.setName("Болдуин");
//        expected.setCapacity(100);        
        Teacher actual = teacherDAO.findOne(6);
        assertEquals(expected.getName(), actual.getName());
        System.out.println("expected " + expected);
        System.out.println("actual " + actual);
        System.out.println("actual " + actual.getCourses());
    }
    
    @Test
    public void testFindOneClassItemInDB() throws DAOException {
        ClassItem expected = new ClassItem();
        expected.setId(9);
//        expected.setName("Болдуин");
//        expected.setCapacity(100);        
        ClassItem actual = classItemDAO.findOne(9);
        assertEquals(expected.getId(), actual.getId());
        System.out.println("expected " + expected);
        System.out.println("actual " + actual);
//        System.out.println("actual " + actual.getCourses());
    }
}
