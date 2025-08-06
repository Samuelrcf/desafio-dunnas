package com.dunnas.desafio.components.supplier.application.mappers;

import com.dunnas.desafio.components.supplier.application.usecases.outputs.CreateSupplierUseCaseOutput;
import com.dunnas.desafio.components.supplier.domain.models.Supplier;
import com.dunnas.desafio.components.user.application.mappers.UserDomainMapper;
import com.dunnas.desafio.components.user.application.usecases.outputs.CreateUserOutput;

public class SupplierDomainMapper {
    private final UserDomainMapper userDomainMapper;

    public SupplierDomainMapper(UserDomainMapper userDomainMapper) {
        this.userDomainMapper = userDomainMapper;
    }

    public CreateSupplierUseCaseOutput domainToOutput(Supplier domain) {
        CreateUserOutput userOutput = userDomainMapper.domainToOutput(domain.getUser());

        return new CreateSupplierUseCaseOutput(domain.getId(), domain.getName(), domain.getCnpj(), userOutput);
    }
}
