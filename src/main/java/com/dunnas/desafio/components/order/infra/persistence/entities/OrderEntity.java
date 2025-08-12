package com.dunnas.desafio.components.order.infra.persistence.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.dunnas.desafio.components.client.infra.persistence.entities.ClientEntity;
import com.dunnas.desafio.components.product.infra.persistence.entities.ProductEntity;
import com.dunnas.desafio.components.supplier.infra.persistence.entities.SupplierEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_orders")
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private UUID orderCode;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private SupplierEntity supplierEntity;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private ClientEntity clientEntity;
	
    @ManyToMany
    @JoinTable(
        name = "tb_orders_products", 
        joinColumns = @JoinColumn(name = "order_id"), 
        inverseJoinColumns = @JoinColumn(name = "product_id") 
    )
	private List<ProductEntity> products;
	
	@OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
	private List<OrderItemEntity> items;
	
	private BigDecimal total;
	private LocalDateTime creationDate;
}
