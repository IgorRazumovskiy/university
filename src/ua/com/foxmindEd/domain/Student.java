package ua.com.foxmindEd.domain;

public class Student {
	private Integer id;
	private String name;

	public Student() {

	}

	public Student(Integer id, String name) {
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
		Student student = (Student) o;
		if (!(getId().equals(student.getId()))) {
			return false;
		}
		return true;
	}

	public int hashcode() {
		int hash = 3;
		hash = 31 * hash + ((getId() != null) ? getId().hashCode() : 0);
		return hash;
	}

}
