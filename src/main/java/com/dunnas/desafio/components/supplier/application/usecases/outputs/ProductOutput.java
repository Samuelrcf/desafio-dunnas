package com.dunnas.desafio.components.supplier.application.usecases.outputs;

import java.math.BigDecimal;

public record ProductOutput(Long id, String name, String description, BigDecimal price) {

}
