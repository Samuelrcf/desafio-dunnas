package com.dunnas.desafio.components.order.application.usecases.outputs;

import java.math.BigDecimal;

public record ProductOutput(Long id, String name, BigDecimal price) {

}
