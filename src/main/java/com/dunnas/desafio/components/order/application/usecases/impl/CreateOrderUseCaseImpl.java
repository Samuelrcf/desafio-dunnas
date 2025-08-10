package com.dunnas.desafio.components.order.application.usecases.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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

	public CreateOrderUseCaseImpl(OrderRepositoryGateway orderRepositoryGateway, OrderDomainMapper orderDomainMapper,
			CurrentUserProvider currentUserProvider, ClientRepositoryGateway clientRepositoryGateway,
			ProductRepositoryGateway productRepositoryGateway, SupplierRepositoryGateway supplierRepositoryGateway) {
		this.orderRepositoryGateway = orderRepositoryGateway;
		this.orderDomainMapper = orderDomainMapper;
		this.currentUserProvider = currentUserProvider;
		this.clientRepositoryGateway = clientRepositoryGateway;
		this.productRepositoryGateway = productRepositoryGateway;
		this.supplierRepositoryGateway = supplierRepositoryGateway;
	}

	@Override
	public List<CreateOrderUseCaseOutput> execute(CreateOrderUseCaseInput input) throws Exception {
		
		Long currentUserId = currentUserProvider.getCurrentUserId().orElseThrow(() -> new UnauthorizedException("Usuário não autenticado"));
		
		Client client = clientRepositoryGateway.findByUserEntityId(currentUserId).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
		
	    //Client client = clientRepositoryGateway.findByUserEntityId(1L).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));

	    List<Long> productIds = input.products().stream()
	        .map(ProductQuantityInput::productId)
	        .toList();

	    List<Product> products = productRepositoryGateway.findAllById(productIds);

	    if (products.isEmpty()) {
	        throw new ObjectNotFoundException("Nenhum produto encontrado");
	    }

	    Map<Supplier, List<ProductQuantityInput>> produtosPorFornecedor =
	        input.products().stream()
	            .collect(Collectors.groupingBy(
	                pq -> {
	                    Product product = products.stream()
	                        .filter(pr -> pr.getId().equals(pq.productId()))
	                        .findFirst()
	                        .orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado"));
	                    return product.getSupplier();
	                }
	            ));

	    List<CreateOrderUseCaseOutput> outputs = new ArrayList<>();

	    for (Map.Entry<Supplier, List<ProductQuantityInput>> entry : produtosPorFornecedor.entrySet()) {
	        Supplier supplier = entry.getKey();

	        supplier = supplierRepositoryGateway.findById(supplier.getId())
	            .orElseThrow(() -> new ObjectNotFoundException("Fornecedor não encontrado"));

	        List<OrderItem> items = entry.getValue().stream().map(pq -> {
	            Product product = products.stream()
	                .filter(pr -> pr.getId().equals(pq.productId()))
	                .findFirst()
	                .orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado"));

	            BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(pq.quantity()));
	            return new OrderItem(product, pq.quantity(), subtotal);
	        }).toList();

	        BigDecimal total = items.stream()
	            .map(OrderItem::getSubtotal)
	            .reduce(BigDecimal.ZERO, BigDecimal::add);

	        client.decreaseBalance(total);

	        Order order = new Order(
	            UUID.randomUUID(),
	            client,
	            supplier,
	            items.stream().map(OrderItem::getProduct).toList(),
	            items,
	            total,
	            LocalDateTime.now()
	        );

	        Order createdOrder = orderRepositoryGateway.create(order);

	        outputs.add(orderDomainMapper.domainToCreateOrderUseCaseOutput(createdOrder));
	    }

	    clientRepositoryGateway.updateBalance(client);

	    return outputs;
	}

}
