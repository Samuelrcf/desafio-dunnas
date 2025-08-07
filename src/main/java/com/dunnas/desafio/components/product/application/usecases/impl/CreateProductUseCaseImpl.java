package com.dunnas.desafio.components.product.application.usecases.impl;

import com.dunnas.desafio.components.product.application.gateways.ProductRepositoryGateway;
import com.dunnas.desafio.components.product.application.mappers.ProductDomainMapper;
import com.dunnas.desafio.components.product.application.usecases.CreateProductUseCase;
import com.dunnas.desafio.components.product.application.usecases.inputs.CreateProductUseCaseInput;
import com.dunnas.desafio.components.product.application.usecases.outputs.CreateProductUseCaseOutput;
import com.dunnas.desafio.components.product.domain.models.Product;
import com.dunnas.desafio.components.supplier.domain.models.Supplier;
import com.dunnas.desafio.components.supplier.infra.persistence.entities.SupplierEntity;
import com.dunnas.desafio.components.supplier.infra.persistence.mappers.SupplierEntityMapper;
import com.dunnas.desafio.components.supplier.infra.persistence.repositories.SupplierRepository;
import com.dunnas.desafio.shared.audit.CurrentUserProvider;
import com.dunnas.desafio.shared.exceptions.ObjectNotFoundException;

public class CreateProductUseCaseImpl implements CreateProductUseCase {

	private final ProductRepositoryGateway productRepositoryGateway;
	private final ProductDomainMapper productDomainMapper;
	private final CurrentUserProvider currentUserProvider;
	private final SupplierRepository supplierRepository;
	private final SupplierEntityMapper supplierEntityMapper;

	public CreateProductUseCaseImpl(ProductRepositoryGateway productRepositoryGateway,
			ProductDomainMapper productDomainMapper, CurrentUserProvider currentUserProvider, SupplierRepository supplierRepository, SupplierEntityMapper supplierEntityMapper) {
		this.productRepositoryGateway = productRepositoryGateway;
		this.productDomainMapper = productDomainMapper;
		this.currentUserProvider = currentUserProvider;
		this.supplierRepository = supplierRepository;
		this.supplierEntityMapper = supplierEntityMapper;
	}

	@Override
	public CreateProductUseCaseOutput execute(CreateProductUseCaseInput input) throws Exception {

		//Long currentUserId = currentUserProvider.getCurrentUserId().orElseThrow(() -> new UnauthorizedException("Usuário não autenticado"));
		
		//SupplierEntity supplierEntity = supplierRepository.findById(currentUserId).orElseThrow(() -> new ObjectNotFoundException("Usuário não autenticado"));
		
		SupplierEntity supplierEntity = supplierRepository.findById(1L).orElseThrow(() -> new ObjectNotFoundException("Usuário não autenticado")); //excluir
		
		Supplier supplier = supplierEntityMapper.entityToModel(supplierEntity);

		Product product = new Product(input.name(), input.description(), input.price(), supplier);

		Product createdProduct = productRepositoryGateway.create(product);
		return productDomainMapper.domainToOutput(createdProduct);
	}
}
