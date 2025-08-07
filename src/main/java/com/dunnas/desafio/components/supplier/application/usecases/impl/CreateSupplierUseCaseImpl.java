package com.dunnas.desafio.components.supplier.application.usecases.impl;

import com.dunnas.desafio.components.supplier.application.gateways.SupplierRepositoryGateway;
import com.dunnas.desafio.components.supplier.application.mappers.SupplierDomainMapper;
import com.dunnas.desafio.components.supplier.application.usecases.CreateSupplierUseCase;
import com.dunnas.desafio.components.supplier.application.usecases.inputs.CreateSupplierUseCaseInput;
import com.dunnas.desafio.components.supplier.application.usecases.outputs.CreateSupplierUseCaseOutput;
import com.dunnas.desafio.components.supplier.domain.models.Supplier;
import com.dunnas.desafio.components.user.domain.models.User;
import com.dunnas.desafio.shared.exceptions.ObjectAlreadyExistsException;
import com.dunnas.desafio.shared.security.PasswordHasher;

public class CreateSupplierUseCaseImpl implements CreateSupplierUseCase {

	private final SupplierRepositoryGateway supplierRepositoryGateway;
	private final SupplierDomainMapper supplierDomainMapper;
	private final PasswordHasher passwordHasher;

	public CreateSupplierUseCaseImpl(SupplierRepositoryGateway supplierRepositoryGateway,
			SupplierDomainMapper supplierDomainMapper, PasswordHasher passwordHasher) {
		this.supplierRepositoryGateway = supplierRepositoryGateway;
		this.supplierDomainMapper = supplierDomainMapper;
		this.passwordHasher = passwordHasher;
	}

	@Override
	public CreateSupplierUseCaseOutput execute(CreateSupplierUseCaseInput input) throws Exception {
		String hashedPassword = passwordHasher.hash(input.user().password());

		User user = new User(input.user().userName(), hashedPassword, input.user().role());

		Supplier supplier = new Supplier(input.name(), input.cnpj(), user);

		if (supplierRepositoryGateway.findByCnpj(supplier.getCnpj()).isPresent()) {
			throw new ObjectAlreadyExistsException("Usuário já cadastrado com esse CNPJ.");
		}

		Supplier createdSupplier = supplierRepositoryGateway.create(supplier);
		return supplierDomainMapper.domainToOutput(createdSupplier);
	}
}
