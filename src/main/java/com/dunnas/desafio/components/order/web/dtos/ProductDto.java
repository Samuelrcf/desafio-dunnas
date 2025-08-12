package com.dunnas.desafio.components.order.web.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDto {
	private final Long id;
    private final String name;
    private final BigDecimal price;
}
