package com.dunnas.desafio.components.product.infra.persistence.gateways;

import java.util.Optional;

import com.dunnas.desafio.components.product.application.gateways.ProductRepositoryGateway;
import com.dunnas.desafio.components.product.domain.models.Product;

public class ProductRepositoryGatewayImpl implements ProductRepositoryGateway{

	@Override
	public Product create(Product Product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product update(Product Product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Product> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Product> findByName(String name) {
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
