package com.dmillerw.wac.network;

public class ProtocalException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ProtocalException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ProtocalException(String message) {
		super(message);
	}
	
	public ProtocalException(Throwable cause) {
		super(cause);
	}
	
}
