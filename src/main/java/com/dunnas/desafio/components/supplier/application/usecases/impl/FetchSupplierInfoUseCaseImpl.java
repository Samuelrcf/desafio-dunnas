package com.dunnas.desafio.components.supplier.application.usecases.impl;

import com.dunnas.desafio.components.supplier.application.gateways.SupplierRepositoryGateway;
import com.dunnas.desafio.components.supplier.application.mappers.SupplierDomainMapper;
import com.dunnas.desafio.components.supplier.application.usecases.FetchSupplierInfoUseCase;
import com.dunnas.desafio.components.supplier.application.usecases.outputs.FetchSupplierInfoUseCaseOutput;
import com.dunnas.desafio.components.supplier.domain.models.Supplier;
import com.dunnas.desafio.shared.audit.CurrentUserProvider;
import com.dunnas.desafio.shared.exceptions.ObjectNotFoundException;
import com.dunnas.desafio.shared.exceptions.UnauthorizedException;

public class FetchSupplierInfoUseCaseImpl implements FetchSupplierInfoUseCase{

	private final SupplierRepositoryGateway supplierRepositoryGateway;
	private final SupplierDomainMapper supplierDomainMapper;
	private final CurrentUserProvider currentUserProvider;
	
	public FetchSupplierInfoUseCaseImpl(SupplierRepositoryGateway supplierRepositoryGateway,
			SupplierDomainMapper supplierDomainMapper, CurrentUserProvider currentUserProvider) {
		this.supplierRepositoryGateway = supplierRepositoryGateway;
		this.supplierDomainMapper = supplierDomainMapper;
		this.currentUserProvider = currentUserProvider;
	}

	@Override
	public FetchSupplierInfoUseCaseOutput execute() {

		Long currentUserId = currentUserProvider.getCurrentUserId().orElseThrow(() -> new UnauthorizedException("Usuário não autenticado"));
		
		Supplier supplier = supplierRepositoryGateway.findByUserEntityId(currentUserId).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
		
		return supplierDomainMapper.domainToFetchSupplierInfoUseCaseOutput(supplier);
	}
}
