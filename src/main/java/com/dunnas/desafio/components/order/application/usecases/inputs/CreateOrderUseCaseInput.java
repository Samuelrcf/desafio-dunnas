package com.dunnas.desafio.components.order.application.usecases.inputs;

import java.util.List;

public record CreateOrderUseCaseInput(List<ProductQuantityInput> products) {

}
