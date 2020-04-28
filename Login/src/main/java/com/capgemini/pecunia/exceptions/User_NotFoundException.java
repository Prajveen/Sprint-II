package com.capgemini.pecunia.exceptions;

@SuppressWarnings("serial")
public class User_NotFoundException extends Exception {
	public User_NotFoundException(String errorMsg){
		super(errorMsg);
	}
}
