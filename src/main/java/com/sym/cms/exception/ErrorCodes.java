package com.sym.cms.exception;


/**
 * Error codes to be thrown along with exception
 * @author ranveer
 *
 */
public enum ErrorCodes {
	
	UNKNOWN_ERROR("system error"),
	CONTACT_NOT_SYNCED("local contact is not synced, please sync before update"),
	INSUFFICIENT_PARAMS("insufficient parameters"),
	CONTACT_ALREADY_EXIST("Contact already exists"),
	CONTACT_DOES_NOT_EXIST("The contact does not exist in the system");
	
	
	private String errorMsg;

	private ErrorCodes(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	

}
