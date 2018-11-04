package ua.com.foxmindEd.domainlayer;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
	private Integer id;
	private String name;
	private List<Course> courses = new ArrayList<>();

	public Teacher(Integer id, String name, List<Course> courses) {
		this.setId(id);
		this.setName(name);
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

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public void addCourse(Course course) {
		courses.add(course);
	}

	public void removeCourse(Course course) {
		courses.remove(course);
	}

}
