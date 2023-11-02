package com.developer.agenda.exceptions;

public class CustomNotFoundException extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;

	public CustomNotFoundException(String message) {
        super(message);
    }

}
