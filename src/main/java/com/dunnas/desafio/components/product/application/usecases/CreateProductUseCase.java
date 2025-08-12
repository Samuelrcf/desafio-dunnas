package com.dunnas.desafio.components.product.application.usecases;

import com.dunnas.desafio.components.product.application.usecases.inputs.CreateProductUseCaseInput;
import com.dunnas.desafio.components.product.application.usecases.outputs.CreateProductUseCaseOutput;

public interface CreateProductUseCase {
    CreateProductUseCaseOutput execute(CreateProductUseCaseInput input) throws Exception;
}
