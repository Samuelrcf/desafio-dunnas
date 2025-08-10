package com.dunnas.desafio.components.product.web.dtos;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private List<ReadDiscountDto> discounts;
    private List<ReadCouponDto> coupons;
}
