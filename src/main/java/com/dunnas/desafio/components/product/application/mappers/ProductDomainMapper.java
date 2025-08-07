package com.dunnas.desafio.components.product.application.mappers;

import com.dunnas.desafio.components.product.application.usecases.outputs.CreateProductUseCaseOutput;
import com.dunnas.desafio.components.product.domain.models.Product;

public class ProductDomainMapper {


    public CreateProductUseCaseOutput domainToOutput(Product domain) {

        return new CreateProductUseCaseOutput(domain.getId(), domain.getName(), domain.getDescription(), domain.getPrice());
    }
}
