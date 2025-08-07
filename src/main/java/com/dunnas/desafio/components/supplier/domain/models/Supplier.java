package com.dunnas.desafio.components.supplier.domain.models;

import java.util.List;
import java.util.Objects;

import com.dunnas.desafio.components.order.domain.models.Order;
import com.dunnas.desafio.components.product.domain.models.Product;
import com.dunnas.desafio.components.user.domain.models.User;

public class Supplier {

	private Long id;
	private String name;
	private String cnpj;
	private List<Product> products;
	private List<Order> orders;
	private User user;
	
	public Supplier(Long id, String name, String cnpj, List<Product> products, User user) {
		this.id = id;
		this.name = name;
		this.cnpj = cnpj;
		this.products = products;
		this.setUser(user);
	}
	
	public Supplier(Long id, String name, String cnpj, User user) {
		this.id = id;
		this.name = name;
		this.cnpj = cnpj;
		this.setUser(user);
	}
	
	public Supplier(String name, String cnpj, User user) {
		this.name = name;
		this.cnpj = cnpj;
		this.setUser(user);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cnpj, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supplier other = (Supplier) obj;
		return Objects.equals(cnpj, other.cnpj) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

}
