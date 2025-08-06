package com.dunnas.desafio.components.user.application.mappers;

import com.dunnas.desafio.components.user.application.usecases.outputs.CreateUserOutput;
import com.dunnas.desafio.components.user.domain.models.User;

public class UserDomainMapper {

    public CreateUserOutput domainToOutput(User domain) {
        return new CreateUserOutput(domain.getId(), domain.getUserName(), domain.getRole());
    }
}
