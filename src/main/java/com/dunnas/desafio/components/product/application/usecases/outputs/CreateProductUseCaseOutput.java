package com.dunnas.desafio.components.product.application.usecases.outputs;

import java.math.BigDecimal;

public record CreateProductUseCaseOutput(Long id, String name, String description, BigDecimal price) {

}
