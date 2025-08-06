package com.dunnas.desafio.components.client.application.mappers;

import com.dunnas.desafio.components.client.application.usecases.outputs.CreateClientUseCaseOutput;
import com.dunnas.desafio.components.client.domain.models.Client;
import com.dunnas.desafio.components.user.application.mappers.UserDomainMapper;
import com.dunnas.desafio.components.user.application.usecases.outputs.CreateUserOutput;

public class ClientDomainMapper {
    private final UserDomainMapper userDomainMapper;

    public ClientDomainMapper(UserDomainMapper userDomainMapper) {
        this.userDomainMapper = userDomainMapper;
    }

    public CreateClientUseCaseOutput domainToOutput(Client domain) {
        CreateUserOutput userOutput = userDomainMapper.domainToOutput(domain.getUser());

        return new CreateClientUseCaseOutput(domain.getId(), domain.getName(), domain.getCpf(), domain.getBirthDate(), domain.getBalance(), userOutput);
    }
}
