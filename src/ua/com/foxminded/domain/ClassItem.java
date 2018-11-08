package ua.com.foxminded.domain;

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

	public ClassItem(Course course, Teacher teacher, Classroom classroom, Group group, LocalDateTime dateTime) {
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

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassItem other = (ClassItem) obj;
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
		return "ClassItem [id=" + id + ", course=" + course + ", teacher=" + teacher + ", classroom=" + classroom
				+ ", group=" + group + ", dateTime=" + dateTime + "]";
	}

}
