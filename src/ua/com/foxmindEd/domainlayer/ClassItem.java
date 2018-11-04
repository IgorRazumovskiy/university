package ua.com.foxmindEd.domainlayer;

import java.time.LocalDateTime;

public class ClassItem {
	private Course course;
	private Teacher teacher;
	private Classroom classroom;
	private Group group;
	private LocalDateTime dateTime;

	public ClassItem(Course course, Teacher teacher, Classroom classroom, Group group, LocalDateTime dateTime) {
		this.setCourse(course);
		this.setTeacher(teacher);
		this.setClassroom(classroom);
		this.setGroup(group);
		this.setDateTime(dateTime);
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

}
