package application;

import javafx.beans.property.StringProperty;

public class Users {

	private int id;
	private String name;
	private String email;
	private String mob;
	private String gender;
	private String dob;
	private String password;
	
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Users(String name, String dob, String mob, String email, String gender) {
		super();
		this.name = name;
		this.dob = dob;
		this.mob = mob;
		this.email = email;
		this.gender = gender;
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
	public String nameProperty() {
        return name;
    }
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
