package com.dunnas.desafio.components.product.domain.models;

import java.util.Objects;

public class Coupon {
    private Long id;
    private String name;
    private String code;
    private Discount discount;

    public Coupon(Long id, String name, String code, Discount discount) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Discount getDiscount() {
        return discount;
    }

	@Override
	public int hashCode() {
		return Objects.hash(code, discount, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coupon other = (Coupon) obj;
		return Objects.equals(code, other.code) && Objects.equals(discount, other.discount)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
    
}
