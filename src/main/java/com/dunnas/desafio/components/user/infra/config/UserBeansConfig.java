package com.dunnas.desafio.components.user.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dunnas.desafio.components.user.application.mappers.UserDomainMapper;

@Configuration
public class UserBeansConfig {

    @Bean
    UserDomainMapper userDomainMapper() {
    	return new UserDomainMapper();
    }
}
