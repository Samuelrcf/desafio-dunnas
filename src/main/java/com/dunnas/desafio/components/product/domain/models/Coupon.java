package com.dunnas.desafio.components.product.domain.models;

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
}
