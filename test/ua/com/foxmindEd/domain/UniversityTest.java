package ua.com.foxmindEd.domain;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ua.com.foxmindEd.domain.ClassItem;
import ua.com.foxmindEd.domain.Classroom;
import ua.com.foxmindEd.domain.Course;
import ua.com.foxmindEd.domain.Group;
import ua.com.foxmindEd.domain.Student;
import ua.com.foxmindEd.domain.Teacher;
import ua.com.foxmindEd.domain.University;

public class UniversityTest {

	University university = new University();
	Course course1 = new Course(1, "course1");
	Course course2 = new Course(2, "course2");
	Course course3 = new Course(3, "course3");
	Student student1 = new Student(1, "student1");
	Student student2 = new Student(2, "student2");
	Student student3 = new Student(3, "student3");
	Student student4 = new Student(4, "student4");
	Student student5 = new Student(5, "student5");
	Student student6 = new Student(6, "student6");
	Classroom classroom1 = new Classroom(1, "classroom1", 50);
	Classroom classroom2 = new Classroom(2, "classroom2", 50);
	Classroom classroom3 = new Classroom(3, "classroom3", 50);
	List<Course> courses1 = new ArrayList<>();
	List<Course> courses2 = new ArrayList<>();
	List<Course> courses3 = new ArrayList<>();
	Teacher teacher1 = new Teacher(1, "teacher1", courses1);
	Teacher teacher2 = new Teacher(2, "teacher2", courses2);
	Teacher teacher3 = new Teacher(3, "teacher3", courses3);
	List<Student> studentsgroup1 = new ArrayList<>();
	List<Student> studentsgroup2 = new ArrayList<>();
	List<Student> studentsgroup3 = new ArrayList<>();
	Group group1 = new Group(1, "group1", studentsgroup1);
	Group group2 = new Group(2, "group2", studentsgroup2);
	Group group3 = new Group(3, "group3", studentsgroup3);

	@Test(expected = NullPointerException.class)
	public void retrieveIsLessonInPeriodWhenInputIsNull() {
		university.isLessonInPeriod(null, null, null);
	}

	@Test
	public void retrieveIsLessonInPeriodWhenDateBetweenPeriod() {
		LocalDateTime dateTimeStart = LocalDateTime.of(2018, 11, 1, 9, 0, 0);
		LocalDateTime dateTimeEnd = LocalDateTime.of(2018, 11, 30, 9, 0, 0);
		LocalDateTime dateTimelesson = LocalDateTime.of(2018, 11, 15, 10, 30, 0);
		teacher1.addCourse(course1);
		teacher1.addCourse(course2);
		teacher2.addCourse(course1);
		teacher2.addCourse(course2);
		teacher2.addCourse(course3);
		teacher3.addCourse(course3);
		group1.addStudent(student1);
		group1.addStudent(student2);
		group1.addStudent(student3);
		group2.addStudent(student4);
		group2.addStudent(student5);
		group3.addStudent(student6);
		ClassItem lesson = new ClassItem(1, course1, teacher1, classroom1, group1, dateTimelesson);

		assertTrue(university.isLessonInPeriod(dateTimeStart, dateTimeEnd, lesson));
	}

	@Test
	public void retrieveIsLessonInPeriodWhenDateNotInPeriod() {
		LocalDateTime dateTimeStart = LocalDateTime.of(2018, 11, 1, 9, 0, 0);
		LocalDateTime dateTimeEnd = LocalDateTime.of(2018, 11, 30, 9, 0, 0);
		LocalDateTime dateTimelesson = LocalDateTime.of(2018, 10, 15, 10, 30, 0);
		teacher1.addCourse(course1);
		teacher1.addCourse(course2);
		teacher2.addCourse(course1);
		teacher2.addCourse(course2);
		teacher2.addCourse(course3);
		teacher3.addCourse(course3);
		group1.addStudent(student1);
		group1.addStudent(student2);
		group1.addStudent(student3);
		group2.addStudent(student4);
		group2.addStudent(student5);
		group3.addStudent(student6);
		ClassItem lesson = new ClassItem(1, course1, teacher1, classroom1, group1, dateTimelesson);

		assertFalse(university.isLessonInPeriod(dateTimeStart, dateTimeEnd, lesson));
	}

	@Test
	public void retrieveSheduleTeacherWithCorrectTeacher() {
		LocalDateTime dateTimeStart = LocalDateTime.of(2018, 11, 1, 9, 0, 0);
		LocalDateTime dateTimeEnd = LocalDateTime.of(2018, 11, 30, 9, 0, 0);
		LocalDateTime dateTimelesson1 = LocalDateTime.of(2018, 11, 15, 10, 30, 0);
		LocalDateTime dateTimelesson2 = LocalDateTime.of(2018, 11, 15, 12, 0, 0);
		LocalDateTime dateTimelesson3 = LocalDateTime.of(2018, 11, 16, 9, 0, 0);
		LocalDateTime dateTimelesson4 = LocalDateTime.of(2018, 11, 17, 14, 0, 0);
		teacher1.addCourse(course1);
		teacher1.addCourse(course2);
		teacher2.addCourse(course1);
		teacher2.addCourse(course2);
		teacher2.addCourse(course3);
		teacher3.addCourse(course3);
		group1.addStudent(student1);
		group1.addStudent(student2);
		group1.addStudent(student3);
		group2.addStudent(student4);
		group2.addStudent(student5);
		group3.addStudent(student6);
		ClassItem lesson1 = new ClassItem(1, course1, teacher1, classroom1, group1, dateTimelesson1);
		ClassItem lesson2 = new ClassItem(2, course2, teacher1, classroom2, group3, dateTimelesson2);
		ClassItem lesson3 = new ClassItem(3, course1, teacher2, classroom2, group2, dateTimelesson3);
		ClassItem lesson4 = new ClassItem(4, course3, teacher3, classroom3, group3, dateTimelesson4);
		university.addTimetable(lesson1);
		university.addTimetable(lesson2);
		university.addTimetable(lesson3);
		university.addTimetable(lesson4);

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
		LocalDateTime dateTimelesson1 = LocalDateTime.of(2018, 11, 15, 10, 30, 0);
		LocalDateTime dateTimelesson2 = LocalDateTime.of(2018, 11, 15, 12, 0, 0);
		LocalDateTime dateTimelesson3 = LocalDateTime.of(2018, 11, 16, 9, 0, 0);
		LocalDateTime dateTimelesson4 = LocalDateTime.of(2018, 11, 17, 14, 0, 0);
		teacher1.addCourse(course1);
		teacher1.addCourse(course2);
		teacher2.addCourse(course1);
		teacher2.addCourse(course2);
		teacher2.addCourse(course3);
		teacher3.addCourse(course3);
		group1.addStudent(student1);
		group1.addStudent(student2);
		group1.addStudent(student3);
		group2.addStudent(student4);
		group2.addStudent(student5);
		group3.addStudent(student6);
		ClassItem lesson1 = new ClassItem(1, course1, teacher1, classroom1, group1, dateTimelesson1);
		ClassItem lesson2 = new ClassItem(2, course2, teacher1, classroom2, group3, dateTimelesson2);
		ClassItem lesson3 = new ClassItem(3, course1, teacher2, classroom2, group2, dateTimelesson3);
		ClassItem lesson4 = new ClassItem(4, course3, teacher3, classroom3, group1, dateTimelesson4);
		university.addTimetable(lesson1);
		university.addTimetable(lesson2);
		university.addTimetable(lesson3);
		university.addTimetable(lesson4);

		List<ClassItem> expected = new ArrayList<>();
		expected.add(lesson1);
		expected.add(lesson4);

		List<ClassItem> actual = university.displayScheduleStudent(dateTimeStart, dateTimeEnd, student1);

		assertEquals(expected, actual);

		List<ClassItem> unexpected = new ArrayList<>();
		expected.add(lesson2);
		expected.add(lesson3);

		assertNotEquals(unexpected, actual);
	}

}
