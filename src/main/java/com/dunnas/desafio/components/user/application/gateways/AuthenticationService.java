package com.dunnas.desafio.components.user.application.gateways;

import com.dunnas.desafio.components.user.domain.models.User;

public interface AuthenticationService {
	User authenticate(String username, String password) throws Exception;
}

