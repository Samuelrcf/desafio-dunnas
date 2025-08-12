package com.dunnas.desafio.components.product.application.usecases.impl;

import java.util.Optional;

import com.dunnas.desafio.components.product.application.gateways.ProductRepositoryGateway;
import com.dunnas.desafio.components.product.application.mappers.ProductDomainMapper;
import com.dunnas.desafio.components.product.application.usecases.CreateDiscountUseCase;
import com.dunnas.desafio.components.product.application.usecases.inputs.CreateDiscountUseCaseInput;
import com.dunnas.desafio.components.product.application.usecases.outputs.CreateProductUseCaseOutput;
import com.dunnas.desafio.components.product.domain.models.Discount;
import com.dunnas.desafio.components.product.domain.models.Product;
import com.dunnas.desafio.shared.exceptions.ApplicationException;

public class CreateDiscountUseCaseImpl implements CreateDiscountUseCase {

    private final ProductRepositoryGateway productRepositoryGateway;
    private final ProductDomainMapper productDomainMapper;

    public CreateDiscountUseCaseImpl(ProductRepositoryGateway productRepositoryGateway, ProductDomainMapper productDomainMapper) {
        this.productRepositoryGateway = productRepositoryGateway;
        this.productDomainMapper = productDomainMapper;
    }

    @Override
    public CreateProductUseCaseOutput execute(CreateDiscountUseCaseInput input) {
        boolean productExists = productRepositoryGateway.existsById(input.productId());
        if (!productExists) {
            throw new ApplicationException(
                    "Não foi possível criar o desconto para o produto informado, pois o produto "
                            + "não existe.");
        }

        Discount discount = new Discount(null, input.value());

        Optional<Product> foundProduct = productRepositoryGateway.findById(input.productId());
        foundProduct.get().addDiscount(discount);

        Product updatedProduct = productRepositoryGateway.update(foundProduct.get());
        return productDomainMapper.domainToOutput(updatedProduct);
    }
}
