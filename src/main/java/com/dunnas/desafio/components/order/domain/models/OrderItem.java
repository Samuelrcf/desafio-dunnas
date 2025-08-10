package com.dunnas.desafio.components.order.domain.models;

import java.math.BigDecimal;
import java.util.Objects;

import com.dunnas.desafio.components.product.domain.models.Product;

public class OrderItem {

	private Long id;
	private Order order;
	private int quantity;
	private BigDecimal subtotal;
	private Product product;
	
	public OrderItem(Long id, Product product, int quantity, BigDecimal subtotal) {
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}
	
	public OrderItem(Product product, int quantity, BigDecimal subtotal) {
		this.quantity = quantity;
		this.subtotal = subtotal;
		this.setProduct(product);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, order, quantity, subtotal);
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id) && Objects.equals(order, other.order) && quantity == other.quantity
				&& Objects.equals(subtotal, other.subtotal);
	}
	
    public static OrderItem from(Product product, int quantity) {
        return new OrderItem(product, quantity, product.calculateSubtotal(quantity));
    }

}
