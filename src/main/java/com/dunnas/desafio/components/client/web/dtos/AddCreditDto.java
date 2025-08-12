package com.dunnas.desafio.components.client.web.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddCreditDto {

	@NotNull
	@DecimalMin("0.01")
	private final BigDecimal amount;
}
