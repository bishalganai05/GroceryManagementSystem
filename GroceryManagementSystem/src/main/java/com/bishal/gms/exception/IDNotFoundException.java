package com.bishal.gms.exception;

public class IDNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	String errMessageString;
	

	public IDNotFoundException(String errMessageString) {
		this.errMessageString = errMessageString;
	}

	public String getErrMessageString() {
		return errMessageString;
	}

	public void setErrMessageString(String errMessageString) {
		this.errMessageString = errMessageString;
	}
	
	

}
