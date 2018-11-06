package ua.com.foxmindEd.domain;

import java.time.LocalDateTime;

public class ClassItem {
	private Integer id;
	private Course course;
	private Teacher teacher;
	private Classroom classroom;
	private Group group;
	private LocalDateTime dateTime;

	public ClassItem() {

	}

	public ClassItem(Integer id, Course course, Teacher teacher, Classroom classroom, Group group,
			LocalDateTime dateTime) {
		this.setId(id);
		this.setCourse(course);
		this.setTeacher(teacher);
		this.setClassroom(classroom);
		this.setGroup(group);
		this.setDateTime(dateTime);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		ClassItem classItem = (ClassItem) o;
		if (!(getId().equals(classItem.getId()))) {
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
