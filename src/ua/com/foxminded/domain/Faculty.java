package ua.com.foxminded.domain;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
	private Integer id;
	private String name;
	private List<Group> groups = new ArrayList<>();
	private List<Classroom> classrooms = new ArrayList<>();
	private List<Chair> chairs = new ArrayList<>();

	public Faculty() {

	}

	public Faculty(String name, List<Group> groups, List<Classroom> classrooms, List<Chair> chairs) {
		this.setName(name);
		this.setGroups(groups);
		this.setClassrooms(classrooms);
		this.setChairs(chairs);
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

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public void addGroup(Group group) {
		groups.add(group);
	}

	public void removeGroup(Group group) {
		groups.remove(group);
	}

	public List<Classroom> getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(List<Classroom> classrooms) {
		this.classrooms = classrooms;
	}

	public void addClassroom(Classroom classroom) {
		classrooms.add(classroom);
	}

	public void removeClassroom(Classroom classroom) {
		classrooms.remove(classroom);
	}

	public List<Chair> getChairs() {
		return chairs;
	}

	public void setChairs(List<Chair> chairs) {
		this.chairs = chairs;
	}

	public void addChair(Chair chair) {
		chairs.add(chair);
	}

	public void removeChair(Chair chair) {
		chairs.remove(chair);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String toString() {
		return "Faculty [id=" + id + ", name=" + name + ", groups=" + groups + ", classrooms=" + classrooms
				+ ", chairs=" + chairs + "]";
	}

}
