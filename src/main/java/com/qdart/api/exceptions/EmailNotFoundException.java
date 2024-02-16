package com.qdart.api.exceptions;

public class EmailNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public EmailNotFoundException(String email) {
		super("No podemos encontrar al usuario con el email " + email);
	}
}
