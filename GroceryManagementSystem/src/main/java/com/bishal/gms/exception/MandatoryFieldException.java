package com.bishal.gms.exception;

public class MandatoryFieldException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	String errMessage;

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public MandatoryFieldException(String errMessage) {
		this.errMessage = errMessage;
	}
	
	
}
