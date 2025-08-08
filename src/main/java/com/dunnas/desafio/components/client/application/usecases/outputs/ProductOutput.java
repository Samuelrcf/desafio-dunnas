package com.dunnas.desafio.components.client.application.usecases.outputs;

import java.math.BigDecimal;

public record ProductOutput(Long id, String name, String description, BigDecimal price) {

}
