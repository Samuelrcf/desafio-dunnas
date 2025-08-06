package com.dunnas.desafio.components.supplier.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dunnas.desafio.components.supplier.application.gateways.SupplierRepositoryGateway;
import com.dunnas.desafio.components.supplier.application.mappers.SupplierDomainMapper;
import com.dunnas.desafio.components.supplier.application.usecases.CreateSupplierUseCase;
import com.dunnas.desafio.components.supplier.application.usecases.impl.CreateSupplierUseCaseImpl;
import com.dunnas.desafio.components.user.application.mappers.UserDomainMapper;
import com.dunnas.desafio.shared.security.PasswordHasher;

@Configuration
public class SupplierBeansConfig {

    @Bean
    CreateSupplierUseCase createSupplierUseCase(SupplierRepositoryGateway supplierRepositoryGateway, SupplierDomainMapper supplierDomainMapper, PasswordHasher passwordHasher) {
        return new CreateSupplierUseCaseImpl(supplierRepositoryGateway, supplierDomainMapper, passwordHasher);
    }
    
    @Bean
    SupplierDomainMapper supplierDomainMapper(UserDomainMapper userDomainMapper) {
    	return new SupplierDomainMapper(userDomainMapper);
    }
    
}
