package com.capgemini.pecunia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AccountBalance")	
public class Account {
	@Id
	@Column(length=12)
	private String AccountID;
	@Column(length = 12)
	private Double balance; 
	
	public String getAccountID() {
		return AccountID;
	}
	public void setAccountID(String accountID) {
		AccountID = accountID;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
		
}
