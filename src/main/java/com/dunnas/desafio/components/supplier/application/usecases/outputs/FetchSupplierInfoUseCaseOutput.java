package com.dunnas.desafio.components.supplier.application.usecases.outputs;

import com.dunnas.desafio.components.user.application.usecases.outputs.FetchUserOutput;

public record FetchSupplierInfoUseCaseOutput(Long id, String name, String cnpj, FetchUserOutput user) {

}
