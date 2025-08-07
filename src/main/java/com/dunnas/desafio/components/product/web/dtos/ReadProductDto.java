package com.dunnas.desafio.components.product.web.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ReadProductDto {

	private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;
}
