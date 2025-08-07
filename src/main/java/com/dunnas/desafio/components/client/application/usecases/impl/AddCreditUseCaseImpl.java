package com.dunnas.desafio.components.client.application.usecases.impl;

import com.dunnas.desafio.components.client.application.gateways.ClientRepositoryGateway;
import com.dunnas.desafio.components.client.application.mappers.ClientDomainMapper;
import com.dunnas.desafio.components.client.application.usecases.AddCreditUseCase;
import com.dunnas.desafio.components.client.application.usecases.inputs.AddCreditUseCaseInput;
import com.dunnas.desafio.components.client.application.usecases.outputs.AddCreditUseCaseOutput;
import com.dunnas.desafio.components.client.domain.models.Client;
import com.dunnas.desafio.shared.exceptions.ObjectNotFoundException;

public class AddCreditUseCaseImpl implements AddCreditUseCase {

	private final ClientRepositoryGateway clientRepositoryGateway;
	private final ClientDomainMapper clientDomainMapper;

	public AddCreditUseCaseImpl(ClientRepositoryGateway clientRepositoryGateway,
			ClientDomainMapper clientDomainMapper) {
		this.clientRepositoryGateway = clientRepositoryGateway;
		this.clientDomainMapper = clientDomainMapper;
	}

	@Override
	public AddCreditUseCaseOutput execute(AddCreditUseCaseInput input) throws Exception {

		//Long currentUserId = currentUserProvider.getCurrentUserId().orElseThrow(() -> new UnauthorizedException("Usuário não autenticado"));
		
		//ClientEntity clientEntity = clientRepository.findById(currentUserId).orElseThrow(() -> new ObjectNotFoundException("Usuário não autenticado"));
		
		Client client = clientRepositoryGateway.findById(1L).orElseThrow(() -> new ObjectNotFoundException("Usuário não autenticado")); //excluir
		
		client.setBalance(input.amount());
		
		Client updatedClient = clientRepositoryGateway.create(client);
		
		return clientDomainMapper.domainToAddCreditUseCaseOutput(updatedClient);
	}
}
