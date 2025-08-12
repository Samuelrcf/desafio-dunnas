package com.dunnas.desafio.components.user.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dunnas.desafio.components.user.application.exceptions.TokenGenerationException;
import com.dunnas.desafio.components.user.application.exceptions.TokenValidationException;
import com.dunnas.desafio.components.user.application.gateways.TokenProvider;
import com.dunnas.desafio.components.user.domain.models.User;

@Service
public class JwtTokenProvider implements TokenProvider{
	
	@Value("${jwt.secret}")
	private String secret;

	@Override
	public String generateToken(User user) throws TokenGenerationException {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		String token = JWT.create()
				.withIssuer("dunnas-api")
				.withSubject(user.getUserName())
				.withExpiresAt(generateExpirationDate())
				.sign(algorithm);
		return token;
	}
	
	@Override
	public String validateToken(String token) throws TokenValidationException{
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return JWT.require(algorithm)
				.withIssuer("dunnas-api")
				.build()
				.verify(token)
				.getSubject();
		
	}
	
	private Instant generateExpirationDate() {
		return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
	}
}
