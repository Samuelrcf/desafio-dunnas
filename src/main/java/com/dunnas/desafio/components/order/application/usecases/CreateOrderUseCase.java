package com.dunnas.desafio.components.order.application.usecases;

import java.util.List;

import com.dunnas.desafio.components.order.application.usecases.inputs.CreateOrderUseCaseInput;
import com.dunnas.desafio.components.order.application.usecases.outputs.CreateOrderUseCaseOutput;

public interface CreateOrderUseCase {
    List<CreateOrderUseCaseOutput> execute(CreateOrderUseCaseInput input) throws Exception;
}
