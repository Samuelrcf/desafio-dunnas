package com.dunnas.desafio.components.order.infra.persistence.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.client.infra.persistence.mappers.ClientEntityMapper;
import com.dunnas.desafio.components.order.domain.models.Order;
import com.dunnas.desafio.components.order.domain.models.OrderItem;
import com.dunnas.desafio.components.order.infra.persistence.entities.OrderEntity;
import com.dunnas.desafio.components.order.infra.persistence.entities.OrderItemEntity;
import com.dunnas.desafio.components.product.infra.persistence.mappers.ProductEntityMapper;
import com.dunnas.desafio.components.supplier.infra.persistence.mappers.SupplierEntityMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrderEntityMapper {
	
	private final ClientEntityMapper clientEntityMapper;
	private final SupplierEntityMapper supplierEntityMapper;
	private final ProductEntityMapper productEntityMapper;
	private final OrderItemEntityMapper orderItemEntityMapper;

	public OrderEntity modelToEntity(Order order) {
	    if (order == null) return null;

	    OrderEntity entity = new OrderEntity();
	    entity.setId(order.getId());
	    entity.setClientEntity(clientEntityMapper.modelToEntity(order.getClient()));
	    entity.setSupplierEntity(supplierEntityMapper.modelToEntity(order.getSupplier()));
	    entity.setCreationDate(order.getCreationDate());
	    entity.setProducts(productEntityMapper.modelListToEntityList(order.getProducts()));
	    entity.setTotal(order.getTotal());
	    entity.setOrderCode(order.getOrderCode());

	    // Mapeia os itens e seta a referência de volta para o pedido
	    List<OrderItemEntity> itemEntities = new ArrayList<>();
	    for (OrderItem item : order.getOrderItems()) {
	        OrderItemEntity itemEntity = orderItemEntityMapper.modelToEntity(item);
	        itemEntity.setOrderEntity(entity); // <-- ESSENCIAL
	        itemEntities.add(itemEntity);
	    }

	    entity.setItems(itemEntities);

	    return entity;
	}

    public Order entityToModel(OrderEntity entity) {
        if (entity == null) return null;

        return new Order(
            entity.getId(),
            clientEntityMapper.entityToModel(entity.getClientEntity()),
            supplierEntityMapper.entityToModel(entity.getSupplierEntity()),
            entity.getCreationDate(),
            productEntityMapper.entityListToModelList(entity.getProducts()),
            entity.getTotal(),
            entity.getOrderCode(),
            orderItemEntityMapper.entityListToModelList(entity.getItems())
        );
    }

}

