package com.dunnas.desafio.components.product.infra.persistence.gateways;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.product.application.gateways.ProductRepositoryGateway;
import com.dunnas.desafio.components.product.domain.models.Product;
import com.dunnas.desafio.components.product.infra.persistence.entities.ProductEntity;
import com.dunnas.desafio.components.product.infra.persistence.mappers.ProductEntityMapper;
import com.dunnas.desafio.components.product.infra.persistence.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductRepositoryGatewayImpl implements ProductRepositoryGateway{

    private final ProductRepository repository;
    private final ProductEntityMapper mapper;
	
	@Override
	public Product create(Product product) {
		ProductEntity entity = mapper.modelToEntity(product); 
		repository.save(entity);
		return mapper.entityToModel(entity);
	}

	@Override
	public Optional<Product> findById(Long id) {
        Optional<ProductEntity> optionalEntity = repository.findById(id);

        if (optionalEntity.isPresent()) {
            ProductEntity entity = optionalEntity.get();
            Product model = mapper.entityToModel(entity);

            return Optional.of(model);
        }
        
        return Optional.empty();
	}

	@Override
	public Optional<Product> findByName(String name) {
		Optional<ProductEntity> optionalEntity = repository.findByName(name);
		
        if (optionalEntity.isPresent()) {
            ProductEntity entity = optionalEntity.get();
            Product model = mapper.entityToModel(entity);

            return Optional.of(model);
        }
        
        return Optional.empty();
	}

	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

	@Override
	public List<Product> findAllById(List<Long> ids) {
	    List<ProductEntity> entities = repository.findAllById(ids);
	    return entities.stream()
	                   .map(mapper::entityToModel)
	                   .toList();
	}

	@Override
	public List<Product> findAll() {
		List<ProductEntity> entities = repository.findByDeletedFalse();
		return entities.stream()
				.map(mapper::entityToModel)
				.toList();
	}

	@Override
	public List<Product> findAllBySupplierId(Long id) {
		List<ProductEntity> entities = repository.findBySupplierEntityIdAndDeletedFalse(id);
		return entities.stream()
				.map(mapper::entityToModel)
				.toList();
	}

	@Override
	public Product update(Product Product) {
		ProductEntity entity = mapper.modelToEntity(Product);

		repository.save(entity);
		return mapper.entityToModel(entity);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
