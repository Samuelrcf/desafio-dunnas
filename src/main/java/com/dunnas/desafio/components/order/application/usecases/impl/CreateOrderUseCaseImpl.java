package com.dunnas.desafio.components.order.application.usecases.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.dunnas.desafio.components.client.application.gateways.ClientRepositoryGateway;
import com.dunnas.desafio.components.client.domain.models.Client;
import com.dunnas.desafio.components.order.application.gateways.OrderRepositoryGateway;
import com.dunnas.desafio.components.order.application.mappers.OrderDomainMapper;
import com.dunnas.desafio.components.order.application.usecases.CreateOrderUseCase;
import com.dunnas.desafio.components.order.application.usecases.inputs.CreateOrderUseCaseInput;
import com.dunnas.desafio.components.order.application.usecases.inputs.ProductQuantityInput;
import com.dunnas.desafio.components.order.application.usecases.outputs.CreateOrderUseCaseOutput;
import com.dunnas.desafio.components.order.domain.models.Order;
import com.dunnas.desafio.components.order.domain.models.OrderItem;
import com.dunnas.desafio.components.order.domain.services.OrderCreationService;
import com.dunnas.desafio.components.product.application.gateways.ProductRepositoryGateway;
import com.dunnas.desafio.components.product.domain.models.Product;
import com.dunnas.desafio.components.supplier.application.gateways.SupplierRepositoryGateway;
import com.dunnas.desafio.components.supplier.domain.models.Supplier;
import com.dunnas.desafio.shared.audit.CurrentUserProvider;
import com.dunnas.desafio.shared.exceptions.ObjectNotFoundException;
import com.dunnas.desafio.shared.exceptions.UnauthorizedException;

public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

	private final OrderRepositoryGateway orderRepositoryGateway;
	private final OrderDomainMapper orderDomainMapper;
	private final CurrentUserProvider currentUserProvider;
	private final ClientRepositoryGateway clientRepositoryGateway;
	private final SupplierRepositoryGateway supplierRepositoryGateway;
	private final ProductRepositoryGateway productRepositoryGateway;
	private final OrderCreationService orderCreationService;

	public CreateOrderUseCaseImpl(OrderRepositoryGateway orderRepositoryGateway, OrderDomainMapper orderDomainMapper,
			CurrentUserProvider currentUserProvider, ClientRepositoryGateway clientRepositoryGateway,
			ProductRepositoryGateway productRepositoryGateway, SupplierRepositoryGateway supplierRepositoryGateway, OrderCreationService orderCreationService) {
		this.orderRepositoryGateway = orderRepositoryGateway;
		this.orderDomainMapper = orderDomainMapper;
		this.currentUserProvider = currentUserProvider;
		this.clientRepositoryGateway = clientRepositoryGateway;
		this.productRepositoryGateway = productRepositoryGateway;
		this.supplierRepositoryGateway = supplierRepositoryGateway;
		this.orderCreationService = orderCreationService;
	}

	@Override
	public List<CreateOrderUseCaseOutput> execute(CreateOrderUseCaseInput input) throws Exception {
	    Long currentUserId = currentUserProvider.getCurrentUserId()
	            .orElseThrow(() -> new UnauthorizedException("Usuário não autenticado"));

	    Client client = clientRepositoryGateway.findByUserEntityId(currentUserId)
	            .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));

	    List<Long> productIds = input.products().stream()
	            .map(ProductQuantityInput::productId)
	            .toList();

	    List<Product> products = productRepositoryGateway.findAllById(productIds);
	    if (products.isEmpty()) {
	        throw new ObjectNotFoundException("Nenhum produto encontrado");
	    }

	    // Montar o Set dos produtos com cupom aplicado
	    Set<Long> productsWithCouponApplied = input.products().stream()
	        .filter(ProductQuantityInput::applyCoupon)
	        .map(ProductQuantityInput::productId)
	        .collect(Collectors.toSet());

	    // Agrupar os itens por fornecedor (ajuste esse método para aceitar também o Set e criar OrderItem sem a flag, só com produto e qty)
	    Map<Supplier, List<OrderItem>> groupedItems = orderCreationService.groupItemsBySupplier(input.products(), products);

	    List<CreateOrderUseCaseOutput> outputs = new ArrayList<>();

	    for (Map.Entry<Supplier, List<OrderItem>> entry : groupedItems.entrySet()) {
	        Supplier supplier = supplierRepositoryGateway.findById(entry.getKey().getId())
	                .orElseThrow(() -> new ObjectNotFoundException("Fornecedor não encontrado"));

	        // Use a nova assinatura do método create passando também o Set de produtos com cupom
	        Order order = Order.create(client, supplier, entry.getValue(), productsWithCouponApplied);
	        order = orderRepositoryGateway.create(order);

	        outputs.add(orderDomainMapper.domainToCreateOrderUseCaseOutput(order));
	    }

	    clientRepositoryGateway.updateBalance(client);
	    return outputs;
	}

}
