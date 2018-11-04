package ua.com.foxmindEd.domainlayer;

public class Classroom {
	private Integer id;
	private String name;
	private Integer capacity;

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

}
