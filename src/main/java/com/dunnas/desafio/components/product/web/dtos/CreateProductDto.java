package com.dunnas.desafio.components.product.web.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateProductDto {

	@NotBlank(message = "Digite o nome do produto.")
	private String name;
	
	@NotBlank(message = "Digite a descrição do produto.")
	private String description;
	
	@NotBlank(message = "Digite o preço do produto.")
	private BigDecimal price;
	
}
