package com.capgemini.pecunia.exceptions;
public class RecordErrorResponse extends Exception {
	private static final long serialVersionUID = 1L;
	private int errorCode;
    private String errorMessage;
   
    public int getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
	public RecordErrorResponse() {
		super();
	}
	public RecordErrorResponse(int errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	
}
