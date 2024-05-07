package model_package;

public class Materia {
	int id;
	String name;
	String description;
	int level;
	
	public Materia() {
		// TODO Auto-generated constructor stub
	}
	public Materia(int id, String name, String description, int level) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.level = level;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

}
