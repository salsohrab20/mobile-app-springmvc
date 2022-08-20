package com.salman.srping.pro.ui.model.response;

//All classes used as a response model have Rest suffix (project naming conventions)
// returns all details to be returned . converts incoming java obj into json payload
//this class is used in the UI layer i.e userface layer

public class UserRest {

	private String userId;  //30-40 alphanumeric character,random number generated 
	private String firstName;
	private String lastName;
	private String email;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
