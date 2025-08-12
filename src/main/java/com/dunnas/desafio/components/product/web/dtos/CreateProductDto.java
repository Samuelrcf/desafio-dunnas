package com.dunnas.desafio.components.product.web.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateProductDto {

	@NotBlank(message = "Informe o nome do produto.")
	private String name;
	
	@NotBlank(message = "Informe a descrição do produto.")
	private String description;
	
    @NotNull(message = "Informe o preço do produto.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero.")
    private BigDecimal price;
	
}
