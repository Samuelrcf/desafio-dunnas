package com.dunnas.desafio.components.client.application.usecases;

import com.dunnas.desafio.components.client.application.usecases.input.CreateClientUseCaseInput;
import com.dunnas.desafio.components.client.application.usecases.outputs.CreateClientUseCaseOutput;

public interface CreateClientUseCase {
    CreateClientUseCaseOutput execute(CreateClientUseCaseInput input) throws Exception;
}
