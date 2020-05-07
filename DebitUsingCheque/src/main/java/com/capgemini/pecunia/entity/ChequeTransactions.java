package com.capgemini.pecunia.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ChequeTransactions")
@SequenceGenerator(name="seq", initialValue=1000, allocationSize=1)
public class ChequeTransactions {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@Column(length = 8)
	private int transactionID;
	@Column(length = 20)
	private Date transactionDate;
	@Column(length = 10)
	private String transactionType;
	@Column(length = 6)
	private int chequeID;
	@Column(length = 6)
	private String chequeNo;
	@Column(length = 12)
	private String payeeAccountNo;
	@Column(length = 12)
	private String recipientAccountNo;
	@Column(length=12)
	private double amount;
	@Column(length = 20)
	private String bankName;
	@Column(length=10)
	private String Ifsc;
	@Column(length = 10)
	private Date issueDate;
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public int getChequeID() {
		return chequeID;
	}
	public void setChequeID(int chequeID) {
		this.chequeID = chequeID;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getPayeeAccountNo() {
		return payeeAccountNo;
	}
	public void setPayeeAccountNo(String payeeAccountNo) {
		this.payeeAccountNo = payeeAccountNo;
	}
	public String getRecipientAccountNo() {
		return recipientAccountNo;
	}
	public void setRecipientAccountNo(String recipientAccountNo) {
		this.recipientAccountNo = recipientAccountNo;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getIfsc() {
		return Ifsc;
	}
	public void setIfsc(String ifsc) {
		Ifsc = ifsc;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	
	
	
	
}
