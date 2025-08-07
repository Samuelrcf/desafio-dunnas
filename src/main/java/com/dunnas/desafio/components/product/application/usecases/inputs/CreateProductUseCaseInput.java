package com.dunnas.desafio.components.product.application.usecases.inputs;

import java.math.BigDecimal;

public record CreateProductUseCaseInput(String name, String description, BigDecimal price) {

}
