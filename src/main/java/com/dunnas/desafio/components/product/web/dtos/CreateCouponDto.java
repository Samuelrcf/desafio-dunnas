package com.dunnas.desafio.components.product.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCouponDto {
    private String name;
    private String code;
    private CreateDiscountDto discount;
}

