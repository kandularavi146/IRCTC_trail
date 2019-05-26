package com.ravi.irctc.exception;

public class ARSServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public ARSServiceException(String errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public ARSServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
