package com.dunnas.desafio.components.order.application.usecases.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.dunnas.desafio.components.product.application.gateways.ProductRepositoryGateway;
import com.dunnas.desafio.components.product.domain.models.Product;
import com.dunnas.desafio.components.supplier.domain.models.Supplier;
import com.dunnas.desafio.shared.audit.CurrentUserProvider;
import com.dunnas.desafio.shared.exceptions.ObjectNotFoundException;

public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

	private final OrderRepositoryGateway orderRepositoryGateway;
	private final OrderDomainMapper orderDomainMapper;
	private final CurrentUserProvider currentUserProvider;
	private final ClientRepositoryGateway clientRepositoryGateway;
	private final ProductRepositoryGateway productRepositoryGateway;

	public CreateOrderUseCaseImpl(OrderRepositoryGateway orderRepositoryGateway, OrderDomainMapper orderDomainMapper,
			CurrentUserProvider currentUserProvider, ClientRepositoryGateway clientRepositoryGateway,
			ProductRepositoryGateway productRepositoryGateway) {
		this.orderRepositoryGateway = orderRepositoryGateway;
		this.orderDomainMapper = orderDomainMapper;
		this.currentUserProvider = currentUserProvider;
		this.clientRepositoryGateway = clientRepositoryGateway;
		this.productRepositoryGateway = productRepositoryGateway;
	}

	@Override
	public List<CreateOrderUseCaseOutput> execute(CreateOrderUseCaseInput input) throws Exception {
	    Client client = clientRepositoryGateway.findById(1L)
	        .orElseThrow(() -> new ObjectNotFoundException("Usuário não autenticado")); // simulado

	    // 1. Buscar todos os produtos
	    List<Long> productIds = new ArrayList<>();
	    for (ProductQuantityInput pq : input.products()) {
	        productIds.add(pq.productId());
	    }

	    List<Product> products = productRepositoryGateway.findAllById(productIds);

	    // 2. Mapear por ID
	    Map<Long, Product> productMap = new HashMap<>();
	    for (Product product : products) {
	        productMap.put(product.getId(), product);
	    }

	    // 3. Agrupar os itens por fornecedor
	    Map<Long, List<OrderItem>> supplierToItemsMap = new HashMap<>();

	    for (ProductQuantityInput pq : input.products()) {
	        Product product = productMap.get(pq.productId());
	        if (product == null) {
	            throw new ObjectNotFoundException("Produto não encontrado: ID = " + pq.productId());
	        }

	        Supplier supplier = product.getSupplier();

	        BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(pq.quantity()));
	        OrderItem item = new OrderItem(product, pq.quantity(), subtotal);

	        supplierToItemsMap
	            .computeIfAbsent(supplier.getId(), k -> new ArrayList<>())
	            .add(item);
	    }

	    // 4. Criar um pedido para cada fornecedor
	    List<CreateOrderUseCaseOutput> outputs = new ArrayList<>();

	    for (Map.Entry<Long, List<OrderItem>> entry : supplierToItemsMap.entrySet()) {

	    	List<OrderItem> items = entry.getValue();

	        Supplier supplier = items.get(0).getProduct().getSupplier(); // todos são do mesmo fornecedor

	        BigDecimal total = items.stream()
	            .map(OrderItem::getSubtotal)
	            .reduce(BigDecimal.ZERO, BigDecimal::add);

	        List<Product> orderProducts = items.stream()
	            .map(OrderItem::getProduct)
	            .toList();
	        
	        UUID orderCode = UUID.randomUUID();

	        Order order = new Order(orderCode, client, supplier, orderProducts, items, total, LocalDateTime.now());

	        Order createdOrder = orderRepositoryGateway.create(order);

	        CreateOrderUseCaseOutput output = orderDomainMapper.domainToCreateOrderUseCaseOutput(createdOrder);
	        outputs.add(output);
	    }

	    return outputs;
	}

}
