package com.dunnas.desafio.components.product.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dunnas.desafio.components.product.application.gateways.ProductRepositoryGateway;
import com.dunnas.desafio.components.product.application.mappers.ProductDomainMapper;
import com.dunnas.desafio.components.product.application.usecases.CreateProductUseCase;
import com.dunnas.desafio.components.product.application.usecases.impl.CreateProductUseCaseImpl;
import com.dunnas.desafio.components.supplier.infra.persistence.mappers.SupplierEntityMapper;
import com.dunnas.desafio.components.supplier.infra.persistence.repositories.SupplierRepository;
import com.dunnas.desafio.shared.audit.CurrentUserProvider;

@Configuration
public class ProductBeansConfig {

	@Bean
	CreateProductUseCase createProductUseCase(ProductRepositoryGateway productRepositoryGateway,
			ProductDomainMapper productDomainMapper, CurrentUserProvider currentUserProvider,
			SupplierRepository supplierRepository, SupplierEntityMapper supplierEntityMapper) {
		return new CreateProductUseCaseImpl(productRepositoryGateway, productDomainMapper, currentUserProvider, supplierRepository, supplierEntityMapper);
	}

	@Bean
	ProductDomainMapper productDomainMapper() {
		return new ProductDomainMapper();
	}

}
