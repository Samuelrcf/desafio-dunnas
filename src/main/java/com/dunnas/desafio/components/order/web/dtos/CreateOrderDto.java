package com.dunnas.desafio.components.order.web.dtos;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateOrderDto {

	@Valid
    @NotEmpty(message = "Selecione pelo menos um produto.")
    private final List<ProductQuantityDto> orderProductDto;
    
}
