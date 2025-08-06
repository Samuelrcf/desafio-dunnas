package com.dunnas.desafio.shared.exceptions;

public class ObjectAlreadyExists extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ObjectAlreadyExists(String message) {
		super(message);
	}
}
