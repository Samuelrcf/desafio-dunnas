package com.dunnas.desafio.components.client.infra.persistence.gateways;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.client.application.gateways.ClientRepositoryGateway;
import com.dunnas.desafio.components.client.domain.models.Client;
import com.dunnas.desafio.components.client.infra.persistence.entities.ClientEntity;
import com.dunnas.desafio.components.client.infra.persistence.mappers.ClientEntityMapper;
import com.dunnas.desafio.components.client.infra.persistence.repositories.ClientRepository;
import com.dunnas.desafio.components.order.domain.models.Order;
import com.dunnas.desafio.components.order.infra.persistence.entities.OrderEntity;
import com.dunnas.desafio.components.order.infra.persistence.mappers.OrderEntityMapper;
import com.dunnas.desafio.components.order.infra.persistence.repositories.OrderRepository;
import com.dunnas.desafio.shared.response.PaginationResult;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ClientRepositoryGatewayImpl implements ClientRepositoryGateway{

    private final ClientRepository repository;
    private final OrderRepository orderRepository;
    private final ClientEntityMapper mapper;
    private final OrderEntityMapper orderMapper;
	
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
	public Optional<Client> findByUserEntityId(Long id) {
		Optional<ClientEntity> optionalEntity = repository.findByUserEntityId(id);
		
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

	@Override
	public PaginationResult<Order> getHistory(Long clientId, int page, int size) {
	    Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
	    Page<OrderEntity> pageResult = orderRepository.findByClientEntityId(clientId, pageable);
	    List<Order> orders = pageResult.getContent().stream().map(orderMapper::entityToModel).toList();
	    return new PaginationResult<>(orders, pageResult.getTotalElements());
	}

}
