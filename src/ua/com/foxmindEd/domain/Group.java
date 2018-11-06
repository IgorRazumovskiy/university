package ua.com.foxmindEd.domain;

import java.util.ArrayList;
import java.util.List;

public class Group {
	private Integer id;
	private String name;
	private List<Student> students = new ArrayList<>();

	public Group() {

	}

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

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		Group group = (Group) o;
		if (!(getId().equals(group.getId()))) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		int hash = 3;
		hash = 31 * hash + ((getId() != null) ? getId().hashCode() : 0);
		return hash;
	}

}
