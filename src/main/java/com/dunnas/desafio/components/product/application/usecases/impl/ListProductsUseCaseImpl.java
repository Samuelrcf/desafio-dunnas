package com.dunnas.desafio.components.product.application.usecases.impl;

import com.dunnas.desafio.components.product.application.gateways.ProductRepositoryGateway;
import com.dunnas.desafio.components.product.application.mappers.ProductDomainMapper;
import com.dunnas.desafio.components.product.application.usecases.ListProductsUseCase;
import com.dunnas.desafio.components.product.application.usecases.outputs.CreateProductUseCaseOutput;
import com.dunnas.desafio.components.product.domain.models.Product;
import java.util.List;

public class ListProductsUseCaseImpl implements ListProductsUseCase {
    private final ProductRepositoryGateway productRepositoryGateway;
    private final ProductDomainMapper productDomainMapper;

    public ListProductsUseCaseImpl(ProductRepositoryGateway productRepositoryGateway, ProductDomainMapper productDomainMapper) {
        this.productRepositoryGateway = productRepositoryGateway;
        this.productDomainMapper = productDomainMapper;
    }

    @Override
    public List<CreateProductUseCaseOutput> execute() {
        List<Product> products = productRepositoryGateway.findAll();
        return productDomainMapper.listDomainToOutput(products);
    }
}
