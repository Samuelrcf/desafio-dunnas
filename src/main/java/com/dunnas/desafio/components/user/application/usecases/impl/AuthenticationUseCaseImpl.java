package com.dunnas.desafio.components.user.application.usecases.impl;

import com.dunnas.desafio.components.user.application.gateways.AuthenticationService;
import com.dunnas.desafio.components.user.application.gateways.TokenProvider;
import com.dunnas.desafio.components.user.application.usecases.AuthenticationUseCase;
import com.dunnas.desafio.components.user.application.usecases.inputs.AuthenticationUseCaseInput;
import com.dunnas.desafio.components.user.domain.models.User;

import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationUseCaseImpl implements AuthenticationUseCase {

    private final AuthenticationService authenticationService;
    private final TokenProvider tokenProvider;

    public AuthenticationUseCaseImpl(AuthenticationService authenticationService, TokenProvider tokenProvider) {
		this.authenticationService = authenticationService;
		this.tokenProvider = tokenProvider;
	}

	public void execute(AuthenticationUseCaseInput input, HttpServletResponse response) throws Exception {
        User user = authenticationService.authenticate(input.userName(), input.password());
        String token = tokenProvider.generateToken(user);
        response.setHeader("Authorization", token);
    }
}
