package com.dunnas.desafio.components.user.application.exceptions;

public class UsernameOrPasswordInvalidException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsernameOrPasswordInvalidException(Throwable cause) {
        super("Usuário e/ou senha incorretos.", cause);
    }
}
