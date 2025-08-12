package com.dunnas.desafio.components.order.infra.persistence.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.order.domain.models.OrderItem;
import com.dunnas.desafio.components.order.infra.persistence.entities.OrderItemEntity;
import com.dunnas.desafio.components.product.infra.persistence.mappers.ProductEntityMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrderItemEntityMapper {
	
	private final ProductEntityMapper productEntityMapper;

    public OrderItemEntity modelToEntity(OrderItem orderItem) {
        if (orderItem == null) return null;

        OrderItemEntity entity = new OrderItemEntity();
        entity.setId(orderItem.getId());
        entity.setProductEntity(productEntityMapper.modelToEntity(orderItem.getProduct()));
        entity.setQuantity(orderItem.getQuantity());
        entity.setSubtotal(orderItem.getSubtotal());
        return entity;
    }
    
    public List<OrderItemEntity> modelListToEntityList (List<OrderItem> orderItems){
    	List<OrderItemEntity> orderItemEntities = new ArrayList<>();
    	for(OrderItem orderItem : orderItems) {
    		OrderItemEntity orderItemEntity = modelToEntity(orderItem);
    		orderItemEntities.add(orderItemEntity);
    	}
    	
    	return orderItemEntities;
    }
    
    public OrderItem entityToModel(OrderItemEntity entity) {
        if (entity == null) return null;

        return new OrderItem(
            entity.getId(),
            productEntityMapper.entityToModel(entity.getProductEntity()), // <-- Adicionado
            entity.getQuantity(),
            entity.getSubtotal()
        );
    }

    public List<OrderItem> entityListToModelList (List<OrderItemEntity> orderItemEntities){
    	List<OrderItem> orderItems = new ArrayList<>();
    	for(OrderItemEntity entity : orderItemEntities) {
    		OrderItem orderItem = entityToModel(entity);
    		orderItems.add(orderItem);
    	}
    	
    	return orderItems;
    }
}
