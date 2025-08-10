package com.dunnas.desafio.components.order.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dunnas.desafio.components.client.application.gateways.ClientRepositoryGateway;
import com.dunnas.desafio.components.order.application.gateways.OrderRepositoryGateway;
import com.dunnas.desafio.components.order.application.mappers.OrderDomainMapper;
import com.dunnas.desafio.components.order.application.usecases.CreateOrderUseCase;
import com.dunnas.desafio.components.order.application.usecases.impl.CreateOrderUseCaseImpl;
import com.dunnas.desafio.components.product.application.gateways.ProductRepositoryGateway;
import com.dunnas.desafio.components.supplier.application.gateways.SupplierRepositoryGateway;
import com.dunnas.desafio.shared.audit.CurrentUserProvider;

@Configuration
public class OrderBeansConfig {

	@Bean
	CreateOrderUseCase createOrderUseCase(OrderRepositoryGateway orderRepositoryGateway,
			OrderDomainMapper orderDomainMapper, CurrentUserProvider currentUserProvider,
			ClientRepositoryGateway clientRepositoryGateway, ProductRepositoryGateway productRepositoryGateway, SupplierRepositoryGateway supplierRepositoryGateway) {
		return new CreateOrderUseCaseImpl(orderRepositoryGateway, orderDomainMapper, currentUserProvider, clientRepositoryGateway, productRepositoryGateway, supplierRepositoryGateway);
	}

	@Bean
	OrderDomainMapper orderDomainMapper() {
		return new OrderDomainMapper();
	}
}
