package ua.com.foxmindEd.domainlayer;

import java.util.ArrayList;
import java.util.List;

public class Chair {
	private Integer id;
	private String name;
	private List<Teacher> teachers = new ArrayList<>();
	private List<Course> courses = new ArrayList<>();

	public Chair(Integer id, String name, List<Teacher> teachers, List<Course> courses) {
		this.setId(id);
		this.setName(name);
		this.setTeachers(teachers);
		this.setCourses(courses);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public void addTeacher(Teacher teacher) {
		teachers.add(teacher);
	}

	public void removeTeacher(Teacher teacher) {
		teachers.remove(teacher);
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public void addCourse(Course course) {
		courses.add(course);
	}

	public void removeStudent(Course course) {
		courses.remove(course);
	}

}
