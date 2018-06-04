package com.yash.tcvm.exception;

public class EmptyFileException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2092294413831399550L;

	public EmptyFileException(String message){
		super(message);
	}
}
