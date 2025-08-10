package com.dunnas.desafio.components.product.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dunnas.desafio.components.product.application.gateways.ProductRepositoryGateway;
import com.dunnas.desafio.components.product.application.mappers.ProductDomainMapper;
import com.dunnas.desafio.components.product.application.usecases.CreateCouponUseCase;
import com.dunnas.desafio.components.product.application.usecases.CreateDiscountUseCase;
import com.dunnas.desafio.components.product.application.usecases.CreateProductUseCase;
import com.dunnas.desafio.components.product.application.usecases.DeleteProductUseCase;
import com.dunnas.desafio.components.product.application.usecases.ListProductsBySupplierUseCase;
import com.dunnas.desafio.components.product.application.usecases.ListProductsUseCase;
import com.dunnas.desafio.components.product.application.usecases.impl.CreateCouponUseCaseImpl;
import com.dunnas.desafio.components.product.application.usecases.impl.CreateDiscountUseCaseImpl;
import com.dunnas.desafio.components.product.application.usecases.impl.CreateProductUseCaseImpl;
import com.dunnas.desafio.components.product.application.usecases.impl.DeleteProductUseCaseImpl;
import com.dunnas.desafio.components.product.application.usecases.impl.ListProductsBySupplierUseCaseImpl;
import com.dunnas.desafio.components.product.application.usecases.impl.ListProductsUseCaseImpl;
import com.dunnas.desafio.components.supplier.application.gateways.SupplierRepositoryGateway;
import com.dunnas.desafio.shared.audit.CurrentUserProvider;

@Configuration
public class ProductBeansConfig {

	@Bean
	CreateProductUseCase createProductUseCase(ProductRepositoryGateway productRepositoryGateway,
			ProductDomainMapper productDomainMapper, CurrentUserProvider currentUserProvider,
			SupplierRepositoryGateway supplierRepositoryGateway) {
		return new CreateProductUseCaseImpl(productRepositoryGateway, productDomainMapper, currentUserProvider,
				supplierRepositoryGateway);
	}

	@Bean
	ProductDomainMapper productDomainMapper() {
		return new ProductDomainMapper();
	}

	@Bean
	CreateDiscountUseCase createDiscountUseCase(ProductRepositoryGateway productRepositoryGateway,
			ProductDomainMapper productDomainMapper) {
		return new CreateDiscountUseCaseImpl(productRepositoryGateway, productDomainMapper);
	}

	@Bean
	CreateCouponUseCase createCouponUseCase(ProductRepositoryGateway productRepositoryGateway,
			ProductDomainMapper productDomainMapper) {
		return new CreateCouponUseCaseImpl(productRepositoryGateway, productDomainMapper);
	}

	@Bean
	ListProductsUseCase listProductsUseCase(ProductRepositoryGateway productRepositoryGateway,
			ProductDomainMapper productDomainMapper) {
		return new ListProductsUseCaseImpl(productRepositoryGateway, productDomainMapper);
	}

	@Bean
	ListProductsBySupplierUseCase listProductsBySupplierUseCase(ProductRepositoryGateway productRepositoryGateway,
			ProductDomainMapper productDomainMapper, SupplierRepositoryGateway supplierRepositoryGateway,
			CurrentUserProvider currentUserProvider) {
		return new ListProductsBySupplierUseCaseImpl(productRepositoryGateway, productDomainMapper,
				supplierRepositoryGateway, currentUserProvider);
	}

	@Bean
	DeleteProductUseCase deleteProductUseCase(ProductRepositoryGateway productRepositoryGateway, SupplierRepositoryGateway supplierRepositoryGateway) {
		return new DeleteProductUseCaseImpl(productRepositoryGateway, supplierRepositoryGateway);
	}
}
