package com.yash.tcvm.exception;

public class FileNotExistException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8109771164176925108L;

	public FileNotExistException(String message) {
		super(message);
	}
}
