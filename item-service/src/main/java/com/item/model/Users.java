package com.item.model;

public class Users {
	
	private int id;
	
	private String username;
	
	private String email;
	
	private String password;
	
	public Users() {
		
	}
	
	public Users(int id, String username, String email, String password) {
		
			this.id = id;
			this.username = username;
			this.email = email;
			this.password = password;
	}
	
	public Users(String username, String email, String password) {
		
		this.username = username;
		this.email = email;
		this.password = password;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmial() {
		return email;
	}

	public void setEmial(String emial) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", emial=" + email + ", password=" + password + "]";
	}
	

}
