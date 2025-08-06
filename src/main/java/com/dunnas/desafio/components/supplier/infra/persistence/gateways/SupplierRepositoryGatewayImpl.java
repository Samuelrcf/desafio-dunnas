package com.dunnas.desafio.components.supplier.infra.persistence.gateways;

import java.util.Optional;

import com.dunnas.desafio.components.supplier.application.gateways.SupplierRepositoryGateway;
import com.dunnas.desafio.components.supplier.domain.models.Supplier;

public class SupplierRepositoryGatewayImpl implements SupplierRepositoryGateway{

	@Override
	public Supplier create(Supplier Supplier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Supplier update(Supplier Supplier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Supplier> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Supplier> findByName(String name) {
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
