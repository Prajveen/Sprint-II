package com.capgemini.pecunia.exceptions;

@SuppressWarnings("serial")
public class Account_NotFoundException extends Exception {
	public Account_NotFoundException(String errorMsg){
		super(errorMsg);
	}
}
