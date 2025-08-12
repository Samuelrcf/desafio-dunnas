package com.dunnas.desafio.components.product.application.usecases;

import com.dunnas.desafio.components.product.application.usecases.outputs.CreateProductUseCaseOutput;
import com.dunnas.desafio.components.product.application.usecases.inputs.CreateDiscountUseCaseInput;

public interface CreateDiscountUseCase {
    CreateProductUseCaseOutput execute(CreateDiscountUseCaseInput input);
}
