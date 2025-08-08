package com.dunnas.desafio.components.user.application.usecases;

import com.dunnas.desafio.components.user.application.usecases.inputs.AuthenticationUseCaseInput;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationUseCase {
	public void execute(AuthenticationUseCaseInput input, HttpServletResponse response) throws Exception;
}
