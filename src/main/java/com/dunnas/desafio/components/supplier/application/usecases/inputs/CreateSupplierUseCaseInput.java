package com.dunnas.desafio.components.supplier.application.usecases.inputs;

import com.dunnas.desafio.components.user.application.usecases.inputs.CreateUserInput;

public record CreateSupplierUseCaseInput(String name, String cnpj, CreateUserInput user) {

}
