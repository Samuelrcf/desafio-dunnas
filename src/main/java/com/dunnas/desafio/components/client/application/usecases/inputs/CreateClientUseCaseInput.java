package com.dunnas.desafio.components.client.application.usecases.inputs;

import java.time.LocalDate;

import com.dunnas.desafio.components.user.application.usecases.inputs.CreateUserInput;

public record CreateClientUseCaseInput(String name, String cpf, LocalDate birthDate, CreateUserInput user) {

}
