package com.dunnas.desafio.components.order.web.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductQuantityDto {

	@NotNull(message = "Selecione um produto.")
	private final Long productId;
	@NotNull(message = "Selecione a quantidade do produto.")
	private final int quantity;
	private Boolean applyCoupon;
}
