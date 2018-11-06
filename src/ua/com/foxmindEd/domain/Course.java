package ua.com.foxmindEd.domain;

public class Course {
	private Integer id;
	private String name;

	public Course() {
		
	}
	
	public Course(Integer id, String name) {
		this.setId(id);
		this.setName(name);
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

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		Course course = (Course) o;
		if (!(getId().equals(course.getId()))) {
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
