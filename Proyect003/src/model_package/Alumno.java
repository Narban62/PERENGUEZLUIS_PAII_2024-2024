package model_package;

public class Alumno {
	
	int id;
	String name;
	String lastname;
	int age;
	
	public Alumno() {
		// TODO Auto-generated constructor stub
	}
	
	public Alumno(int id, String name, String lastname, int age) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.age = age;
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
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
