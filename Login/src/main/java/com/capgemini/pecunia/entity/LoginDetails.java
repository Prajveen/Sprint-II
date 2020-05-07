package com.capgemini.pecunia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LoginDetails")	
public class LoginDetails {
	@Id
	@Column(length=8)
	private String employeeID;
	@Column(length=25)
	private String username;
	@Column(length=10)
	private String password;
	
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginDetails [employeeID=" + employeeID + ", username=" + username + ", password=" + password + "]";
	}
	
	
}
