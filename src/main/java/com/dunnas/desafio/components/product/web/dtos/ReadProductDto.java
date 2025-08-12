package com.dunnas.desafio.components.product.web.dtos;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private ReadDiscountDto discount;
    private ReadCouponDto coupon;
}
