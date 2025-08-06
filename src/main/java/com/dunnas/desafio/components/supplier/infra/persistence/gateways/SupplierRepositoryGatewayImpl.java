package com.dunnas.desafio.components.supplier.infra.persistence.gateways;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.supplier.application.gateways.SupplierRepositoryGateway;
import com.dunnas.desafio.components.supplier.domain.models.Supplier;
import com.dunnas.desafio.components.supplier.infra.persistence.entities.SupplierEntity;
import com.dunnas.desafio.components.supplier.infra.persistence.mappers.SupplierEntityMapper;
import com.dunnas.desafio.components.supplier.infra.persistence.repositories.SupplierRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SupplierRepositoryGatewayImpl implements SupplierRepositoryGateway{

    private final SupplierRepository repository;
    private final SupplierEntityMapper mapper;
	
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

}
