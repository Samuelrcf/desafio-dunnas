package com.dunnas.desafio.components.product.web.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadDiscountDto {
    private Long id;
    private BigDecimal value;
    private Long productId;
}
