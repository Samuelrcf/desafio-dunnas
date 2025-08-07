package com.dunnas.desafio.components.order.web.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderItemDto {

	private final ProductDto productDto;
	private int quantity;
	private BigDecimal subtotal;
}
