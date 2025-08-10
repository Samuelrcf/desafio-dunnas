package com.dunnas.desafio.components.order.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.dunnas.desafio.components.client.domain.models.Client;
import com.dunnas.desafio.components.product.domain.models.Product;
import com.dunnas.desafio.components.supplier.domain.models.Supplier;

public class Order {

	private Long id;
	private UUID orderCode;
	private Client client;
	private Supplier supplier;
	private List<Product> products;
	private BigDecimal total;
	private LocalDateTime creationDate;
	private List<OrderItem> orderItems;

	public Order(Long id, Client client, Supplier supplier, LocalDateTime creationDate, List<Product> products, BigDecimal total, UUID orderCode, 
			 List<OrderItem> orderItems) {
		this.id = id;
		this.orderCode = orderCode;
		this.client = client;
		this.supplier = supplier;
		this.products = products;
		this.total = total;
		this.creationDate = creationDate;
		this.orderItems = orderItems;
	}
	
	public Order(UUID orderCode, Client client, Supplier supplier, List<Product> products, List<OrderItem> orderItems, BigDecimal total, LocalDateTime creationDate) {
		this.orderCode = orderCode;
		this.client = client;
		this.setSupplier(supplier);
		this.products = products;
		this.setOrderItems(orderItems);
		this.total = total;
		this.creationDate = creationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public UUID getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(UUID orderCode) {
		this.orderCode = orderCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(client, creationDate, id, orderCode, orderItems, products, supplier, total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(client, other.client) && Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(id, other.id) && Objects.equals(orderCode, other.orderCode)
				&& Objects.equals(orderItems, other.orderItems) && Objects.equals(products, other.products)
				&& Objects.equals(supplier, other.supplier) && Objects.equals(total, other.total);
	}
	
    public static Order create(Client client, Supplier supplier, List<OrderItem> items) {
        BigDecimal total = items.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        client.decreaseBalance(total);

        return new Order(
                UUID.randomUUID(),
                client,
                supplier,
                items.stream().map(OrderItem::getProduct).toList(),
                items,
                total,
                LocalDateTime.now()
        );
    }

}
