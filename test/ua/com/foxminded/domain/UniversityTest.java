package ua.com.foxminded.domain;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ua.com.foxminded.domain.ClassItem;
import ua.com.foxminded.domain.Classroom;
import ua.com.foxminded.domain.Course;
import ua.com.foxminded.domain.Group;
import ua.com.foxminded.domain.Student;
import ua.com.foxminded.domain.Teacher;
import ua.com.foxminded.domain.University;

public class UniversityTest {

    University university = new University();
    Course course1 = new Course("course1");
    Course course2 = new Course("course2");
    Course course3 = new Course("course3");
    IdGenerator genCourse = new IdGenerator();
    {
        course1.setId(genCourse.getNextId());
        course2.setId(genCourse.getNextId());
        course3.setId(genCourse.getNextId());
    }
    Student student1 = new Student("student1");
    Student student2 = new Student("student2");
    Student student3 = new Student("student3");
    Student student4 = new Student("student4");
    Student student5 = new Student("student5");
    Student student6 = new Student("student6");
    IdGenerator genStudent = new IdGenerator();
    {
        student1.setId(genStudent.getNextId());
        student2.setId(genStudent.getNextId());
        student3.setId(genStudent.getNextId());
        student4.setId(genStudent.getNextId());
        student5.setId(genStudent.getNextId());
        student6.setId(genStudent.getNextId());
    }
    Classroom classroom1 = new Classroom("classroom1", 50);
    Classroom classroom2 = new Classroom("classroom2", 50);
    Classroom classroom3 = new Classroom("classroom3", 50);
    IdGenerator genClassroom = new IdGenerator();
    {
        classroom1.setId(genClassroom.getNextId());
        classroom2.setId(genClassroom.getNextId());
        classroom3.setId(genClassroom.getNextId());
    }
    List<Course> courses1 = new ArrayList<>();
    List<Course> courses2 = new ArrayList<>();
    List<Course> courses3 = new ArrayList<>();
    Teacher teacher1 = new Teacher("teacher1", courses1);
    Teacher teacher2 = new Teacher("teacher2", courses2);
    Teacher teacher3 = new Teacher("teacher3", courses3);
    Teacher teacher4 = new Teacher("teacher4", courses3);
    IdGenerator genTeacher = new IdGenerator();
    {
        teacher1.setId(genTeacher.getNextId());
        teacher2.setId(genTeacher.getNextId());
        teacher3.setId(genTeacher.getNextId());
        teacher4.setId(genTeacher.getNextId());
    }
    List<Student> studentsgroup1 = new ArrayList<>();
    List<Student> studentsgroup2 = new ArrayList<>();
    List<Student> studentsgroup3 = new ArrayList<>();
    List<Student> studentsgroup4 = new ArrayList<>();
    Group group1 = new Group("group1", studentsgroup1);
    Group group2 = new Group("group2", studentsgroup2);
    Group group3 = new Group("group3", studentsgroup3);
    Group group4 = new Group("group4", studentsgroup4);
    IdGenerator genGroup = new IdGenerator();
    {
        group1.setId(genGroup.getNextId());
        group2.setId(genGroup.getNextId());
        group3.setId(genGroup.getNextId());
        group4.setId(genGroup.getNextId());
        teacher1.addCourse(course1);
        teacher1.addCourse(course2);
        teacher2.addCourse(course1);
        teacher2.addCourse(course2);
        teacher2.addCourse(course3);
        teacher3.addCourse(course3);
        group1.addStudent(student1);
        group1.addStudent(student2);
        group4.addStudent(student3);
        group2.addStudent(student4);
        group2.addStudent(student5);
        group3.addStudent(student6);
    }
    LocalDateTime dateTimelesson1 = LocalDateTime.of(2018, 11, 15, 10, 30, 0);
    LocalDateTime dateTimelesson2 = LocalDateTime.of(2018, 11, 15, 12, 0, 0);
    LocalDateTime dateTimelesson3 = LocalDateTime.of(2018, 11, 16, 9, 0, 0);
    LocalDateTime dateTimelesson4 = LocalDateTime.of(2018, 11, 17, 14, 0, 0);
    LocalDateTime dateTimelesson5 = LocalDateTime.of(2018, 11, 18, 15, 0, 0);
    ClassItem lesson1 = new ClassItem(course1, teacher1, classroom1, group1, dateTimelesson1);
    ClassItem lesson2 = new ClassItem(course2, teacher1, classroom2, group3, dateTimelesson2);
    ClassItem lesson3 = new ClassItem(course1, teacher2, classroom2, group2, dateTimelesson3);
    ClassItem lesson4 = new ClassItem(course3, teacher3, classroom3, group3, dateTimelesson4);
    ClassItem lesson5 = new ClassItem(course3, teacher3, classroom3, group1, dateTimelesson5);
    IdGenerator genLesson = new IdGenerator();
    {
        lesson1.setId(genLesson.getNextId());
        lesson2.setId(genLesson.getNextId());
        lesson3.setId(genLesson.getNextId());
        lesson4.setId(genLesson.getNextId());
        lesson5.setId(genLesson.getNextId());
        university.addTimetable(lesson1);
        university.addTimetable(lesson2);
        university.addTimetable(lesson3);
        university.addTimetable(lesson4);
        university.addTimetable(lesson5);
    }

    @Test(expected = NullPointerException.class)
    public void retrieveIsLessonInPeriodWhenInputIsNull() {
        university.isLessonInPeriod(null, null, null);
    }

    @Test
    public void retrieveIsLessonInPeriodWhenDateBetweenPeriod() {
        LocalDateTime dateTimeStart = LocalDateTime.of(2018, 11, 1, 9, 0, 0);
        LocalDateTime dateTimeEnd = LocalDateTime.of(2018, 11, 30, 9, 0, 0);

        assertTrue(university.isLessonInPeriod(dateTimeStart, dateTimeEnd, lesson1));
    }

    @Test
    public void retrieveIsLessonInPeriodWhenDateNotInPeriod() {
        LocalDateTime dateTimeStart = LocalDateTime.of(2018, 11, 16, 9, 0, 0);
        LocalDateTime dateTimeEnd = LocalDateTime.of(2018, 11, 30, 9, 0, 0);

        assertFalse(university.isLessonInPeriod(dateTimeStart, dateTimeEnd, lesson1));
    }

    @Test
    public void retrieveSheduleTeacherWithCorrectTeacher() {
        LocalDateTime dateTimeStart = LocalDateTime.of(2018, 11, 1, 9, 0, 0);
        LocalDateTime dateTimeEnd = LocalDateTime.of(2018, 11, 30, 9, 0, 0);

        List<ClassItem> expected = new ArrayList<>();
        expected.add(lesson1);
        expected.add(lesson2);
        List<ClassItem> actual = university.displayScheduleTeacher(dateTimeStart, dateTimeEnd, teacher1);

        assertEquals(expected, actual);

        List<ClassItem> unexpected = new ArrayList<>();
        expected.add(lesson3);
        expected.add(lesson4);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void retrieveSheduleStudentWithCorrectStudent() {
        LocalDateTime dateTimeStart = LocalDateTime.of(2018, 11, 1, 9, 0, 0);
        LocalDateTime dateTimeEnd = LocalDateTime.of(2018, 11, 30, 9, 0, 0);

        List<ClassItem> expected = new ArrayList<>();
        expected.add(lesson1);
        expected.add(lesson5);
        List<ClassItem> actual = university.displayScheduleStudent(dateTimeStart, dateTimeEnd, student1);

        assertEquals(expected, actual);

        List<ClassItem> unexpected = new ArrayList<>();
        expected.add(lesson2);
        expected.add(lesson3);
        expected.add(lesson4);

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void retrieveEmptySheduleForTeacher() {
        LocalDateTime dateTimeStart = LocalDateTime.of(2018, 11, 1, 9, 0, 0);
        LocalDateTime dateTimeEnd = LocalDateTime.of(2018, 11, 30, 9, 0, 0);

        List<ClassItem> actual = university.displayScheduleTeacher(dateTimeStart, dateTimeEnd, teacher4);

        assertTrue(actual.isEmpty());
    }

    @Test
    public void retrieveEmptySheduleForStudent() {
        LocalDateTime dateTimeStart = LocalDateTime.of(2018, 11, 1, 9, 0, 0);
        LocalDateTime dateTimeEnd = LocalDateTime.of(2018, 11, 30, 9, 0, 0);
        System.out.println(dateTimeStart);
        System.out.println(dateTimeEnd);
        
        List<ClassItem> actual = university.displayScheduleStudent(dateTimeStart, dateTimeEnd, student3);

        assertTrue(actual.isEmpty());
    }
}
