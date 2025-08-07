package com.dunnas.desafio.components.order.infra.persistence.gateways;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.order.application.gateways.OrderRepositoryGateway;
import com.dunnas.desafio.components.order.domain.models.Order;
import com.dunnas.desafio.components.order.infra.persistence.entities.OrderEntity;
import com.dunnas.desafio.components.order.infra.persistence.mappers.OrderEntityMapper;
import com.dunnas.desafio.components.order.infra.persistence.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrderRepositoryGatewayImpl implements OrderRepositoryGateway{

    private final OrderRepository repository;
    private final OrderEntityMapper mapper;
	
	@Override
	public Order create(Order order) {
		OrderEntity entity = mapper.modelToEntity(order); 
		repository.save(entity);
		return mapper.entityToModel(entity);
	}

	@Override
	public Optional<Order> findById(Long id) {
        Optional<OrderEntity> optionalEntity = repository.findById(id);

        if (optionalEntity.isPresent()) {
            OrderEntity entity = optionalEntity.get();
            Order model = mapper.entityToModel(entity);

            return Optional.of(model);
        }
        
        return Optional.empty();
	}

	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

}
