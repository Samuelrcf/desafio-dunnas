package com.dunnas.desafio.components.user.application.usecases;

import com.dunnas.desafio.components.user.application.usecases.inputs.AuthenticationUseCaseInput;
import com.dunnas.desafio.components.user.domain.models.User;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationUseCase {
	public User execute(AuthenticationUseCaseInput input, HttpServletResponse response) throws Exception;
}
