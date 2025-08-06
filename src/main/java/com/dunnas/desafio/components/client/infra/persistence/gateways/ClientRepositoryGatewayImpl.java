package com.dunnas.desafio.components.client.infra.persistence.gateways;

import java.util.Optional;

import com.dunnas.desafio.components.client.application.gateways.ClientRepositoryGateway;
import com.dunnas.desafio.components.client.domain.models.Client;

public class ClientRepositoryGatewayImpl implements ClientRepositoryGateway{

	@Override
	public Client create(Client Client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client update(Client Client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Client> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Client> findByName(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
