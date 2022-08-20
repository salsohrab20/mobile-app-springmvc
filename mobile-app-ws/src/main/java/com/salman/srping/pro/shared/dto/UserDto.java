package com.salman.srping.pro.shared.dto;

import java.io.Serializable;

//dataTransferobject class - to share details betwwen different layers
//contains all the details . to be used in all the different layers UI layers,database layer,service layer
// java code will use this class to send datas between different layers

public class UserDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4538972073324835080L;
	
	/*this is becoz dto is data transfer objects and to confirm to that design we need to implement serializable */
	private long id;  //database id 
	private String userId; //id to be returned , alphanumeric to be generated randomly
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String encryptedPassword;
	private String emailVerificationToken;
	private boolean emailVerificationStatus=false;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}
	public void setEmailVerificationStatus(boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
