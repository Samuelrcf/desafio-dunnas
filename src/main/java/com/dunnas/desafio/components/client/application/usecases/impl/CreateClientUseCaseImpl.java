package com.dunnas.desafio.components.client.application.usecases.impl;

import java.math.BigDecimal;

import com.dunnas.desafio.components.client.application.gateways.ClientRepositoryGateway;
import com.dunnas.desafio.components.client.application.mappers.ClientDomainMapper;
import com.dunnas.desafio.components.client.application.usecases.CreateClientUseCase;
import com.dunnas.desafio.components.client.application.usecases.inputs.CreateClientUseCaseInput;
import com.dunnas.desafio.components.client.application.usecases.outputs.CreateClientUseCaseOutput;
import com.dunnas.desafio.components.client.domain.models.Client;
import com.dunnas.desafio.components.user.domain.models.User;
import com.dunnas.desafio.shared.exceptions.ObjectAlreadyExistsException;
import com.dunnas.desafio.shared.security.PasswordHasher;

public class CreateClientUseCaseImpl implements CreateClientUseCase {

	private final ClientRepositoryGateway clientRepositoryGateway;
	private final ClientDomainMapper clientDomainMapper;
	private final PasswordHasher passwordHasher;

	public CreateClientUseCaseImpl(ClientRepositoryGateway clientRepositoryGateway,
			ClientDomainMapper clientDomainMapper, PasswordHasher passwordHasher) {
		this.clientRepositoryGateway = clientRepositoryGateway;
		this.clientDomainMapper = clientDomainMapper;
		this.passwordHasher = passwordHasher;
	}

	@Override
	public CreateClientUseCaseOutput execute(CreateClientUseCaseInput input) throws Exception {
		String hashedPassword = passwordHasher.hash(input.user().password());

		User user = new User(input.user().userName(), hashedPassword, input.user().role());
		BigDecimal balance = BigDecimal.ZERO;

		Client client = new Client(input.name(), input.cpf(), input.birthDate(), balance, user);

		if (clientRepositoryGateway.findByCpf(client.getCpf()).isPresent()) {
			throw new ObjectAlreadyExistsException("Usuário já cadastrado com esse CPF.");
		}
		
		if (clientRepositoryGateway.findByCpf(client.getUser().getUserName()).isPresent()) {
			throw new ObjectAlreadyExistsException("Usuário já cadastrado com esse login.");
		}

		Client createdClient = clientRepositoryGateway.create(client);
		return clientDomainMapper.domainToCreateClientUseCaseOutput(createdClient);
	}
}
