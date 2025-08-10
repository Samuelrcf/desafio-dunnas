package com.dunnas.desafio.components.client.web.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductDto {

	private final Long id;
	private final String name;
	private final String description;
	private final BigDecimal price;
	private final Integer quantity;
}
