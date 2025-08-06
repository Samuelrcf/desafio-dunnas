package com.dunnas.desafio.components.supplier.application.usecases.outputs;

import com.dunnas.desafio.components.user.application.usecases.outputs.CreateUserOutput;

public record CreateSupplierUseCaseOutput(Long id, String name, String cnpj, CreateUserOutput user) {

}
