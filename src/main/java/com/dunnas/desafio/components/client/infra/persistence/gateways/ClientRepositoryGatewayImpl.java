package com.dunnas.desafio.components.client.infra.persistence.gateways;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.client.application.gateways.ClientRepositoryGateway;
import com.dunnas.desafio.components.client.domain.models.Client;
import com.dunnas.desafio.components.client.infra.persistence.entities.ClientEntity;
import com.dunnas.desafio.components.client.infra.persistence.mappers.ClientEntityMapper;
import com.dunnas.desafio.components.client.infra.persistence.repositories.ClientRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ClientRepositoryGatewayImpl implements ClientRepositoryGateway{

    private final ClientRepository repository;
    private final ClientEntityMapper mapper;
	
	@Override
	public Client create(Client client) {
		ClientEntity entity = mapper.modelToEntity(client); 
		repository.save(entity);
		return mapper.entityToModel(entity);
	}

	@Override
	public Optional<Client> findById(Long id) {
        Optional<ClientEntity> optionalEntity = repository.findById(id);

        if (optionalEntity.isPresent()) {
            ClientEntity entity = optionalEntity.get();
            Client model = mapper.entityToModel(entity);

            return Optional.of(model);
        }
        
        return Optional.empty();
	}

	@Override
	public Optional<Client> findByCpf(String cpf) {
		Optional<ClientEntity> optionalEntity = repository.findByCpf(cpf);
		
        if (optionalEntity.isPresent()) {
            ClientEntity entity = optionalEntity.get();
            Client model = mapper.entityToModel(entity);

            return Optional.of(model);
        }
        
        return Optional.empty();
	}

	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

}
