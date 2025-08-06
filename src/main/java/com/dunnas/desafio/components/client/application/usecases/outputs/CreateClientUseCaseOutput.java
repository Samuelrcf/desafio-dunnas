package com.dunnas.desafio.components.client.application.usecases.outputs;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.dunnas.desafio.components.user.application.usecases.outputs.CreateUserOutput;

public record CreateClientUseCaseOutput(Long id, String name, String cpf, LocalDate birthDate, BigDecimal balance, CreateUserOutput user) {

}
