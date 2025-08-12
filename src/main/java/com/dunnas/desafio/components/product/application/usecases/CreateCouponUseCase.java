package com.dunnas.desafio.components.product.application.usecases;

import com.dunnas.desafio.components.product.application.usecases.inputs.CreateCouponUseCaseInput;
import com.dunnas.desafio.components.product.application.usecases.outputs.CreateProductUseCaseOutput;

public interface CreateCouponUseCase {
    CreateProductUseCaseOutput execute(CreateCouponUseCaseInput input);
}
