package com.dunnas.desafio.components.product.domain.models;

import com.dunnas.desafio.shared.exceptions.DomainException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import com.dunnas.desafio.components.supplier.domain.models.Supplier;

public class Product {

	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private Supplier supplier;
	private List<Discount> discounts;
	private List<Coupon> coupons;
	
	public Product(Long id, String name, String description, BigDecimal price, Supplier supplier, List<Discount> discounts, List<Coupon> coupons) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.supplier = supplier;
		this.discounts = discounts;
		this.coupons = coupons;
	}
	
	public Product(String name, String description, BigDecimal price, Supplier supplier) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.supplier = supplier;
	}
	
	public Product(Long id, String name, String description, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, name, price);
	}
	
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(price, other.price);
	}

	public void addDiscount(Discount discount) {
		if(discount == null) {
			throw new DomainException("O desconto não pode ser null.");
		}

		discounts.add(discount);
	}

	public void addCoupon(Coupon coupon) {
		if(coupon == null) {
			throw new DomainException("O cupom não pode ser null.");
		}

		coupons.add(coupon);
	}
	
}
