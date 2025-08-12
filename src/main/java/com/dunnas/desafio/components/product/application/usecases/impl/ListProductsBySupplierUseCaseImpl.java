package com.dunnas.desafio.components.product.application.usecases.impl;

import java.util.List;

import com.dunnas.desafio.components.product.application.gateways.ProductRepositoryGateway;
import com.dunnas.desafio.components.product.application.mappers.ProductDomainMapper;
import com.dunnas.desafio.components.product.application.usecases.ListProductsBySupplierUseCase;
import com.dunnas.desafio.components.product.application.usecases.outputs.CreateProductUseCaseOutput;
import com.dunnas.desafio.components.product.domain.models.Product;
import com.dunnas.desafio.components.supplier.application.gateways.SupplierRepositoryGateway;
import com.dunnas.desafio.components.supplier.domain.models.Supplier;
import com.dunnas.desafio.shared.audit.CurrentUserProvider;
import com.dunnas.desafio.shared.exceptions.ObjectNotFoundException;
import com.dunnas.desafio.shared.exceptions.UnauthorizedException;

public class ListProductsBySupplierUseCaseImpl implements ListProductsBySupplierUseCase {
    private final ProductRepositoryGateway productRepositoryGateway;
    private final SupplierRepositoryGateway supplierRepositoryGateway;
    private final ProductDomainMapper productDomainMapper;
    private final CurrentUserProvider currentUserProvider;

    public ListProductsBySupplierUseCaseImpl(ProductRepositoryGateway productRepositoryGateway, ProductDomainMapper productDomainMapper, SupplierRepositoryGateway supplierRepositoryGateway, CurrentUserProvider currentUserProvider) {
        this.productRepositoryGateway = productRepositoryGateway;
        this.productDomainMapper = productDomainMapper;
        this.supplierRepositoryGateway = supplierRepositoryGateway;
        this.currentUserProvider = currentUserProvider;
    }

    @Override
    public List<CreateProductUseCaseOutput> execute() {
    	
		Long currentUserId = currentUserProvider.getCurrentUserId().orElseThrow(() -> new UnauthorizedException("Usuário não autenticado"));
		
		Supplier supplier = supplierRepositoryGateway.findByUserEntityId(currentUserId).orElseThrow(() -> new ObjectNotFoundException("Fornecedor não encontrado"));
		
		//Supplier supplier = supplierRepositoryGateway.findById(1L).orElseThrow(() -> new ObjectNotFoundException("Usuário não autenticado")); //excluir
    	
        List<Product> products = productRepositoryGateway.findAllBySupplierId(supplier.getId());
        return productDomainMapper.listDomainToOutput(products);
    }
}
