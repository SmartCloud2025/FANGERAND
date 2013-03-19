package cn.cdu.fanger.rest.entity;

import java.io.Serializable;

import cn.cdu.fanger.utill.Gender;

public class AndrUser implements Serializable{
	private static final long serialVersionUID = 3209182876694283259L;
	
	private Integer id;
	private String name;
	private String email;
	private String password;
	private Gender gender;
	
	public AndrUser(){}

	public AndrUser(String name, String email, String password, Gender gender) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
