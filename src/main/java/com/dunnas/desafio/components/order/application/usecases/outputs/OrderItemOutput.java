package com.dunnas.desafio.components.order.application.usecases.outputs;

import java.math.BigDecimal;

public record OrderItemOutput(ProductOutput productOutput, int quantity, BigDecimal subtotal) {

}
