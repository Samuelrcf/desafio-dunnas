package com.dunnas.desafio.components.order.infra.persistence.entities;

import java.math.BigDecimal;

import com.dunnas.desafio.components.product.infra.persistence.entities.ProductEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_orders_items")
public class OrderItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private OrderEntity orderEntity;
	
	private int quantity;
	private BigDecimal subtotal;
	
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;
}
