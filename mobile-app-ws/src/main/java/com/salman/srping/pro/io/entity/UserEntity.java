package com.salman.srping.pro.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//this package will be used to persist data into the db
@Entity(name="users")   //table named user created
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5675731426846311316L;
	
	@Id               //defines it to be unique
	@GeneratedValue   //generate auto increment value
	private long id; //primary key in db ,autoincrement when record inserted
	
	@Column(nullable=false,length=50)   //column cannot be null
	private String firstName;
	
	@Column(nullable=false,length=50)
	private String lastName;
	
	@Column(nullable=false,length=50)
	private String userId;
	
	@Column(nullable=false,length=120)//makes it a unique part,no 2 users with same email can register (unique=true )
	private String email;
	
	@Column(nullable=false)
	private String encryptedPassword;
	
	private String emailVerificationToken;
	
	@Column(nullable=false)
	private boolean emailVerificationStatus=false;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public boolean isEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
