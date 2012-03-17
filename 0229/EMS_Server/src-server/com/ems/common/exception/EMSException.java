package com.ems.common.exception;

public class EMSException extends RuntimeException{

	public EMSException() {
		super();
	}

	public EMSException(String message, Throwable cause) {
		super(message, cause);
	}

	public EMSException(String message) {
		super(message);
	}

	public EMSException(Throwable cause) {
		super(cause);
	}

	
}
