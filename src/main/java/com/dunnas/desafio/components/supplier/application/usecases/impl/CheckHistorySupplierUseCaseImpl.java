package com.dunnas.desafio.components.supplier.application.usecases.impl;

import java.util.List;

import com.dunnas.desafio.components.order.domain.models.Order;
import com.dunnas.desafio.components.supplier.application.gateways.SupplierRepositoryGateway;
import com.dunnas.desafio.components.supplier.application.mappers.SupplierDomainMapper;
import com.dunnas.desafio.components.supplier.application.usecases.CheckHistorySupplierUseCase;
import com.dunnas.desafio.components.supplier.application.usecases.outputs.CheckHistoryUseCaseOutput;
import com.dunnas.desafio.components.supplier.domain.models.Supplier;
import com.dunnas.desafio.shared.audit.CurrentUserProvider;
import com.dunnas.desafio.shared.exceptions.ObjectNotFoundException;
import com.dunnas.desafio.shared.exceptions.UnauthorizedException;
import com.dunnas.desafio.shared.response.PaginationResult;

public class CheckHistorySupplierUseCaseImpl implements CheckHistorySupplierUseCase{
	
	private final SupplierRepositoryGateway supplierRepositoryGateway;
	private final SupplierDomainMapper supplierDomainMapper;
	private final CurrentUserProvider currentUserProvider;

	public CheckHistorySupplierUseCaseImpl(SupplierRepositoryGateway supplierRepositoryGateway,
			SupplierDomainMapper supplierDomainMapper, CurrentUserProvider currentUserProvider) {
		this.supplierRepositoryGateway = supplierRepositoryGateway;
		this.supplierDomainMapper = supplierDomainMapper;
		this.currentUserProvider = currentUserProvider;
	}

	@Override
	public PaginationResult<CheckHistoryUseCaseOutput> execute(int page, int size) throws Exception {
		
		Long currentUserId = currentUserProvider.getCurrentUserId().orElseThrow(() -> new UnauthorizedException("Usuário não autenticado"));
		
		Supplier supplier = supplierRepositoryGateway.findByUserEntityId(currentUserId).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
		
	    PaginationResult<Order> ordersPage = supplierRepositoryGateway.getHistory(supplier.getId(), page, size);

	    List<CheckHistoryUseCaseOutput> outputList = ordersPage.getContent().stream()
	            .map(supplierDomainMapper::domainToCheckHistoryOutput)
	            .toList();

	    return new PaginationResult<>(outputList, ordersPage.getTotalElements());
	}

}
