package com.sym.cms.exception;


/**
 * Exception class to be used for all the thrown exception from the Contact Management system
 * @author ranveer
 *
 */
public class ContactMgmtSystemException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ErrorCodes errorCode;

	public ContactMgmtSystemException(ErrorCodes errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public ErrorCodes getErrorCode() {
		return errorCode;
	}
	

}
