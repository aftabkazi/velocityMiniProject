package com.connection;

public class User {
	
	private String firstName;
	private String lastName;
	private String userName;
	private Integer score;
	
	public User() {
		super();
	}

	public User(String firstName, String lastName, Integer score) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.score = score;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
