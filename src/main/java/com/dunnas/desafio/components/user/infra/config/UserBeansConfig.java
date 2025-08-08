package com.dunnas.desafio.components.user.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dunnas.desafio.components.user.application.gateways.AuthenticationService;
import com.dunnas.desafio.components.user.application.gateways.TokenProvider;
import com.dunnas.desafio.components.user.application.mappers.UserDomainMapper;
import com.dunnas.desafio.components.user.application.usecases.AuthenticationUseCase;
import com.dunnas.desafio.components.user.application.usecases.impl.AuthenticationUseCaseImpl;

@Configuration
public class UserBeansConfig {
	
    @Bean
    AuthenticationUseCase authenticationUseCase(AuthenticationService authenticationService, TokenProvider tokenProvider) {
    	return new AuthenticationUseCaseImpl(authenticationService, tokenProvider);
    }

    @Bean
    UserDomainMapper userDomainMapper() {
    	return new UserDomainMapper();
    }
}
