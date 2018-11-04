package ua.com.foxmindEd.domainlayer;

import java.util.ArrayList;
import java.util.List;

public class Group {
	private Integer id;
	private String name;
	private List<Student> students = new ArrayList<>();

	public Group(Integer id, String name, List<Student> students) {
		this.setId(id);
		this.setName(name);
		this.setStudents(students);
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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public void addStudent(Student student) {
		students.add(student);
	}

	public void removeStudent(Student student) {
		students.remove(student);
	}

}
