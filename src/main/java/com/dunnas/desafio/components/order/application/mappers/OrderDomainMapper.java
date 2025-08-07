package com.dunnas.desafio.components.order.application.mappers;

import java.util.ArrayList;
import java.util.List;

import com.dunnas.desafio.components.order.application.usecases.outputs.ClientOutput;
import com.dunnas.desafio.components.order.application.usecases.outputs.CreateOrderUseCaseOutput;
import com.dunnas.desafio.components.order.application.usecases.outputs.OrderItemOutput;
import com.dunnas.desafio.components.order.application.usecases.outputs.ProductOutput;
import com.dunnas.desafio.components.order.application.usecases.outputs.SupplierOutput;
import com.dunnas.desafio.components.order.domain.models.Order;
import com.dunnas.desafio.components.order.domain.models.OrderItem;

public class OrderDomainMapper {

    public CreateOrderUseCaseOutput domainToCreateOrderUseCaseOutput(Order domain) {
    	
    	ClientOutput clientOutput = new ClientOutput(domain.getClient().getId(), domain.getClient().getName());
    	
    	SupplierOutput supplierOutput = new SupplierOutput(domain.getSupplier().getId(), domain.getSupplier().getName());
    	
    	List<OrderItemOutput> ordersOutput = new ArrayList<>();
    	for(OrderItem order : domain.getOrderItems()) {
    		ProductOutput productOutput = new ProductOutput(order.getProduct().getId(), order.getProduct().getName(), order.getProduct().getPrice());
    		OrderItemOutput orderOutput = new OrderItemOutput(productOutput, order.getQuantity(), order.getSubtotal());
    		ordersOutput.add(orderOutput);
    	}
    	
        return new CreateOrderUseCaseOutput(domain.getOrderCode(), clientOutput, supplierOutput, ordersOutput, domain.getTotal(), domain.getCreationDate());
    }
    
}
