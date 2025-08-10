package com.dunnas.desafio.components.product.application.usecases.inputs;

import java.math.BigDecimal;

public record CreateDiscountUseCaseInput(BigDecimal value, Long productId) {

}
