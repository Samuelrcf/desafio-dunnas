package com.dunnas.desafio.components.supplier.infra.persistence.gateways;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.order.domain.models.Order;
import com.dunnas.desafio.components.order.infra.persistence.entities.OrderEntity;
import com.dunnas.desafio.components.order.infra.persistence.mappers.OrderEntityMapper;
import com.dunnas.desafio.components.order.infra.persistence.repositories.OrderRepository;
import com.dunnas.desafio.components.supplier.application.gateways.SupplierRepositoryGateway;
import com.dunnas.desafio.components.supplier.domain.models.Supplier;
import com.dunnas.desafio.components.supplier.infra.persistence.entities.SupplierEntity;
import com.dunnas.desafio.components.supplier.infra.persistence.mappers.SupplierEntityMapper;
import com.dunnas.desafio.components.supplier.infra.persistence.repositories.SupplierRepository;
import com.dunnas.desafio.shared.response.PaginationResult;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SupplierRepositoryGatewayImpl implements SupplierRepositoryGateway{

    private final SupplierRepository repository;
    private final SupplierEntityMapper mapper;
    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderMapper;
	
	@Override
	public Supplier create(Supplier supplier) {
		SupplierEntity entity = mapper.modelToEntity(supplier); 
		repository.save(entity);
		return mapper.entityToModel(entity);
	}

	@Override
	public Optional<Supplier> findById(Long id) {
        Optional<SupplierEntity> optionalEntity = repository.findById(id);

        if (optionalEntity.isPresent()) {
            SupplierEntity entity = optionalEntity.get();
            Supplier model = mapper.entityToModel(entity);

            return Optional.of(model);
        }
        
        return Optional.empty();
	}

	@Override
	public Optional<Supplier> findByCnpj(String cnpj) {
		Optional<SupplierEntity> optionalEntity = repository.findByCnpj(cnpj);
		
        if (optionalEntity.isPresent()) {
            SupplierEntity entity = optionalEntity.get();
            Supplier model = mapper.entityToModel(entity);

            return Optional.of(model);
        }
        
        return Optional.empty();
	}

	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}
	
	@Override
	public PaginationResult<Order> getHistory(Long supplierId, int page, int size) {
	    Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
	    Page<OrderEntity> pageResult = orderRepository.findByClientEntityId(supplierId, pageable);
	    List<Order> orders = pageResult.getContent().stream().map(orderMapper::entityToModel).toList();
	    return new PaginationResult<>(orders, pageResult.getTotalElements());
	}

}
