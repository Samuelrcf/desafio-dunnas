package com.dunnas.desafio.components.supplier.domain.models;

import java.util.List;
import java.util.Objects;

import com.dunnas.desafio.components.product.domain.models.Product;

public class Supplier {

	private Long id;
	private String name;
	private String cnpj;
	private List<Product> products;
	
	public Supplier(Long id, String name, String cnpj, List<Product> products) {
		this.id = id;
		this.name = name;
		this.cnpj = cnpj;
		this.products = products;
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
