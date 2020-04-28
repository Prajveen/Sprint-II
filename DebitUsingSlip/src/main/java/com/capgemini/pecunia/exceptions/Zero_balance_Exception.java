package com.capgemini.pecunia.exceptions;
@SuppressWarnings("serial")
public class Zero_balance_Exception extends Exception {
	public Zero_balance_Exception(String errorMsg){
		super(errorMsg);
	}
}
