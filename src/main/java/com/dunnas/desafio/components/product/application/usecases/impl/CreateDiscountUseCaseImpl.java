package com.dunnas.desafio.components.product.application.usecases.impl;

import com.dunnas.desafio.components.product.application.gateways.DiscountRepositoryGateway;
import com.dunnas.desafio.components.product.application.gateways.ProductRepositoryGateway;
import com.dunnas.desafio.components.product.application.mappers.ProductDomainMapper;
import com.dunnas.desafio.components.product.application.usecases.outputs.CreateProductUseCaseOutput;
import com.dunnas.desafio.components.product.domain.models.Product;
import com.dunnas.desafio.components.product.application.usecases.CreateDiscountUseCase;
import com.dunnas.desafio.components.product.application.usecases.inputs.CreateDiscountUseCaseInput;
import com.dunnas.desafio.components.product.domain.models.Discount;
import com.dunnas.desafio.shared.exceptions.ApplicationException;
import java.util.Optional;

public class CreateDiscountUseCaseImpl implements CreateDiscountUseCase {

    private final ProductRepositoryGateway productRepositoryGateway;
    private final ProductDomainMapper productDomainMapper;
    private final DiscountRepositoryGateway discountRepositoryGateway;

    public CreateDiscountUseCaseImpl(ProductRepositoryGateway productRepositoryGateway, ProductDomainMapper productDomainMapper, DiscountRepositoryGateway discountRepositoryGateway) {
        this.productRepositoryGateway = productRepositoryGateway;
        this.productDomainMapper = productDomainMapper;
        this.discountRepositoryGateway = discountRepositoryGateway;
    }

    @Override
    public CreateProductUseCaseOutput execute(CreateDiscountUseCaseInput input) {
        boolean productExists = productRepositoryGateway.existsById(input.productId());
        if (!productExists) {
            throw new ApplicationException(
                    "Não foi possível criar o desconto para o produto informado, pois o produto "
                            + "não existe.");
        }

        Discount discount = new Discount(null, input.value(), input.productId());
        discountRepositoryGateway.create(discount);

        Optional<Product> productWithNewDiscount = productRepositoryGateway.findById(input.productId());
        return productDomainMapper.domainToOutput(productWithNewDiscount.get());
    }
}
