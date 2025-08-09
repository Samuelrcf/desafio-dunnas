package com.dunnas.desafio.components.client.application.usecases.impl;

import com.dunnas.desafio.components.client.application.gateways.ClientRepositoryGateway;
import com.dunnas.desafio.components.client.application.mappers.ClientDomainMapper;
import com.dunnas.desafio.components.client.application.usecases.FetchClientInfoUseCase;
import com.dunnas.desafio.components.client.application.usecases.outputs.FetchClientInfoUseCaseOutput;
import com.dunnas.desafio.components.client.domain.models.Client;
import com.dunnas.desafio.shared.audit.CurrentUserProvider;
import com.dunnas.desafio.shared.exceptions.ObjectNotFoundException;
import com.dunnas.desafio.shared.exceptions.UnauthorizedException;

public class FetchClientInfoUseCaseImpl implements FetchClientInfoUseCase{

	private final ClientRepositoryGateway clientRepositoryGateway;
	private final ClientDomainMapper clientDomainMapper;
	private final CurrentUserProvider currentUserProvider;
	
	public FetchClientInfoUseCaseImpl(ClientRepositoryGateway clientRepositoryGateway,
			ClientDomainMapper clientDomainMapper, CurrentUserProvider currentUserProvider) {
		this.clientRepositoryGateway = clientRepositoryGateway;
		this.clientDomainMapper = clientDomainMapper;
		this.currentUserProvider = currentUserProvider;
	}

	@Override
	public FetchClientInfoUseCaseOutput execute() {

		Long currentUserId = currentUserProvider.getCurrentUserId().orElseThrow(() -> new UnauthorizedException("Usuário não autenticado"));
		
		Client client = clientRepositoryGateway.findByUserEntityId(currentUserId).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
		
		return clientDomainMapper.domainToFetchClientInfoUseCaseOutput(client);
	}

}
