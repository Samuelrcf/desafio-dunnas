package com.dunnas.desafio.components.user.application.mappers;

import com.dunnas.desafio.components.user.application.usecases.outputs.CreateUserOutput;
import com.dunnas.desafio.components.user.application.usecases.outputs.FetchUserOutput;
import com.dunnas.desafio.components.user.domain.models.User;

public class UserDomainMapper {

    public CreateUserOutput domainToCreateUserOutput(User domain) {
        return new CreateUserOutput(domain.getId(), domain.getUserName(), domain.getRole());
    }
    
    public FetchUserOutput domainToFetchUserOutput(User domain) {
    	return new FetchUserOutput(domain.getId(), domain.getUserName(), domain.getRole());
    }
}
