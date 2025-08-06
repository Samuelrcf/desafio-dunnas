package com.dunnas.desafio.components.product.infra.persistence.entities;

import java.math.BigDecimal;

import com.dunnas.desafio.components.supplier.infra.persistence.entities.SupplierEntity;

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
@Table(name = "tb_products")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private SupplierEntity supplierEntity;
}
