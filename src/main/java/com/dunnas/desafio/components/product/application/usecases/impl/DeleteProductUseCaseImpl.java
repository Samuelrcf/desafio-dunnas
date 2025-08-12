package com.dunnas.desafio.components.product.application.usecases.impl;

import com.dunnas.desafio.components.product.application.gateways.ProductRepositoryGateway;
import com.dunnas.desafio.components.product.application.usecases.DeleteProductUseCase;
import com.dunnas.desafio.components.supplier.application.gateways.SupplierRepositoryGateway;
import com.dunnas.desafio.shared.exceptions.ObjectNotFoundException;

public class DeleteProductUseCaseImpl implements DeleteProductUseCase{
	private final ProductRepositoryGateway productRepositoryGateway;
	private final SupplierRepositoryGateway supplierRepositoryGateway;

	public DeleteProductUseCaseImpl(ProductRepositoryGateway productRepositoryGateway, SupplierRepositoryGateway supplierRepositoryGateway) {
		this.productRepositoryGateway = productRepositoryGateway;
		this.supplierRepositoryGateway = supplierRepositoryGateway;
	}

	@Override
	public void execute(Long id) throws Exception {
		
		if(!supplierRepositoryGateway.existsByProductEntityId(id)) {
			throw new ObjectNotFoundException("Produto não encontrado.");
		}
		
		productRepositoryGateway.delete(id);
	}
}
