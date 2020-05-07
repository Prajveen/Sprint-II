package com.capgemini.pecunia.exceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {
	public UserNotFoundException(String errorMsg){
		super(errorMsg);
	}
	
	public UserNotFoundException(String msg,Throwable e){
        super(msg,e);
	}
}
