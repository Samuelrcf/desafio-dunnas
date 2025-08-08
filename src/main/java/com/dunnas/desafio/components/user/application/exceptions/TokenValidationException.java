package com.dunnas.desafio.components.user.application.exceptions;

public class TokenValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TokenValidationException(Throwable cause) {
        super("Token inválido ou expirado", cause);
    }
}
