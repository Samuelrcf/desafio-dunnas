package com.dunnas.desafio.components.client.application.usecases.impl;

import java.util.List;

import com.dunnas.desafio.components.client.application.gateways.ClientRepositoryGateway;
import com.dunnas.desafio.components.client.application.mappers.ClientDomainMapper;
import com.dunnas.desafio.components.client.application.usecases.CheckHistoryClientUseCase;
import com.dunnas.desafio.components.client.application.usecases.outputs.CheckHistoryUseCaseOutput;
import com.dunnas.desafio.components.client.domain.models.Client;
import com.dunnas.desafio.components.order.domain.models.Order;
import com.dunnas.desafio.shared.audit.CurrentUserProvider;
import com.dunnas.desafio.shared.exceptions.ObjectNotFoundException;
import com.dunnas.desafio.shared.exceptions.UnauthorizedException;
import com.dunnas.desafio.shared.response.PaginationResult;

public class CheckHistoryClientUseCaseImpl implements CheckHistoryClientUseCase{
	
	private final ClientRepositoryGateway clientRepositoryGateway;
	private final ClientDomainMapper clientDomainMapper;
	private final CurrentUserProvider currentUserProvider;

	public CheckHistoryClientUseCaseImpl(ClientRepositoryGateway clientRepositoryGateway,
			ClientDomainMapper clientDomainMapper, CurrentUserProvider currentUserProvider) {
		this.clientRepositoryGateway = clientRepositoryGateway;
		this.clientDomainMapper = clientDomainMapper;
		this.currentUserProvider = currentUserProvider;
	}

	@Override
	public PaginationResult<CheckHistoryUseCaseOutput> execute(int page, int size) throws Exception {
		
		Long currentUserId = currentUserProvider.getCurrentUserId().orElseThrow(() -> new UnauthorizedException("Usuário não autenticado"));
		
		Client client = clientRepositoryGateway.findById(currentUserId).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
		
		//Client client = clientRepositoryGateway.findById(1L).orElseThrow(() -> new Exception("Cliente não encontrado."));

	    PaginationResult<Order> ordersPage = clientRepositoryGateway.getHistory(client.getId(), page, size);

	    List<CheckHistoryUseCaseOutput> outputList = ordersPage.getContent().stream()
	            .map(clientDomainMapper::domainToCheckHistoryOutput)
	            .toList();

	    return new PaginationResult<>(outputList, ordersPage.getTotalElements());
	}

}
