package com.dunnas.desafio.components.client.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dunnas.desafio.components.client.application.gateways.ClientRepositoryGateway;
import com.dunnas.desafio.components.client.application.mappers.ClientDomainMapper;
import com.dunnas.desafio.components.client.application.usecases.AddCreditUseCase;
import com.dunnas.desafio.components.client.application.usecases.CheckHistoryClientUseCase;
import com.dunnas.desafio.components.client.application.usecases.CreateClientUseCase;
import com.dunnas.desafio.components.client.application.usecases.FetchClientInfoUseCase;
import com.dunnas.desafio.components.client.application.usecases.impl.AddCreditUseCaseImpl;
import com.dunnas.desafio.components.client.application.usecases.impl.CheckHistoryClientUseCaseImpl;
import com.dunnas.desafio.components.client.application.usecases.impl.CreateClientUseCaseImpl;
import com.dunnas.desafio.components.client.application.usecases.impl.FetchClientInfoUseCaseImpl;
import com.dunnas.desafio.components.user.application.mappers.UserDomainMapper;
import com.dunnas.desafio.shared.audit.CurrentUserProvider;
import com.dunnas.desafio.shared.security.PasswordHasher;

@Configuration
public class ClientBeansConfig {

    @Bean
    CreateClientUseCase createClientUseCase(ClientRepositoryGateway clientRepositoryGateway, ClientDomainMapper clientDomainMapper, PasswordHasher passwordHasher) {
        return new CreateClientUseCaseImpl(clientRepositoryGateway, clientDomainMapper, passwordHasher);
    }
    
    @Bean
    AddCreditUseCase addCreditUseCase(ClientRepositoryGateway clientRepositoryGateway, ClientDomainMapper clientDomainMapper, CurrentUserProvider currentUserProvider) {
    	return new AddCreditUseCaseImpl(clientRepositoryGateway, clientDomainMapper, currentUserProvider);
    }
    
    @Bean
    CheckHistoryClientUseCase checkHistoryClientUseCase(ClientRepositoryGateway clientRepositoryGateway, ClientDomainMapper clientDomainMapper, CurrentUserProvider currentUserProvider) {
    	return new CheckHistoryClientUseCaseImpl(clientRepositoryGateway, clientDomainMapper, currentUserProvider);
    }
    
    @Bean
    FetchClientInfoUseCase fetchClientInfoUseCase(ClientRepositoryGateway clientRepositoryGateway, ClientDomainMapper clientDomainMapper, CurrentUserProvider currentUserProvider) {
    	return new FetchClientInfoUseCaseImpl(clientRepositoryGateway, clientDomainMapper, currentUserProvider);
    }
    
    @Bean
    ClientDomainMapper clientDomainMapper(UserDomainMapper userDomainMapper) {
    	return new ClientDomainMapper(userDomainMapper);
    }
    
}
