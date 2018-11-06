package ua.com.foxmindEd.domain;

public class Classroom {
	private Integer id;
	private String name;
	private Integer capacity;

	public Classroom() {

	}

	public Classroom(Integer id, String name, Integer capacity) {
		this.setId(id);
		this.setName(name);
		this.setCapacity(capacity);
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

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		Classroom classroom = (Classroom) o;
		if (!(getId().equals(classroom.getId()))) {
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
