package com.dunnas.desafio.components.supplier.application.usecases;

import com.dunnas.desafio.components.supplier.application.usecases.inputs.CreateSupplierUseCaseInput;
import com.dunnas.desafio.components.supplier.application.usecases.outputs.CreateSupplierUseCaseOutput;

public interface CreateSupplierUseCase {
    CreateSupplierUseCaseOutput execute(CreateSupplierUseCaseInput input) throws Exception;
}
