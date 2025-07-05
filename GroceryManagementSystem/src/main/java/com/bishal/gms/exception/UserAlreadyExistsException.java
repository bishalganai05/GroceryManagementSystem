package com.bishal.gms.exception;

public class UserAlreadyExistsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	String errMessage;

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public UserAlreadyExistsException(String errMessage) {
		this.errMessage = errMessage;
	}
	
	
}
