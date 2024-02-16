package com.qdart.api.exceptions;


//Hereda de RuntimeException
public class AgendaNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public AgendaNotFoundException(Long id) {
		super("La agenda con el ID " + id + " no ha sido encontrada");
	}
}	