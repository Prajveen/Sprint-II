package com.capgemini.pecunia.exceptions;

@SuppressWarnings("serial")
public class AccountNotFoundException extends Exception {
	public AccountNotFoundException(String errorMsg){
		super(errorMsg);
	}
}
