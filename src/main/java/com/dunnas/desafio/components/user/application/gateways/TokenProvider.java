package com.dunnas.desafio.components.user.application.gateways;

import com.dunnas.desafio.components.user.application.exceptions.TokenGenerationException;
import com.dunnas.desafio.components.user.application.exceptions.TokenValidationException;
import com.dunnas.desafio.components.user.domain.models.User;

public interface TokenProvider {

    String generateToken(User user) throws TokenGenerationException;

    String validateToken(String token) throws TokenValidationException;
}

