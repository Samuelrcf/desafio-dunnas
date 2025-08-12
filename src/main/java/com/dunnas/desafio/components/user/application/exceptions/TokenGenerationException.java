package com.dunnas.desafio.components.user.application.exceptions;

public class TokenGenerationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TokenGenerationException(Throwable cause) {
		super("Erro ao gerar token", cause);
	}
}