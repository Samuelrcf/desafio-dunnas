package com.dunnas.desafio.components.product.application.usecases.impl;

import com.dunnas.desafio.components.product.application.gateways.ProductRepositoryGateway;
import com.dunnas.desafio.components.product.application.mappers.ProductDomainMapper;
import com.dunnas.desafio.components.product.application.usecases.CreateCouponUseCase;
import com.dunnas.desafio.components.product.application.usecases.inputs.CreateCouponUseCaseInput;
import com.dunnas.desafio.components.product.application.usecases.outputs.CreateProductUseCaseOutput;
import com.dunnas.desafio.components.product.domain.models.Coupon;
import com.dunnas.desafio.components.product.domain.models.Discount;
import com.dunnas.desafio.components.product.domain.models.Product;
import com.dunnas.desafio.shared.exceptions.ApplicationException;

public class CreateCouponUseCaseImpl implements CreateCouponUseCase {

    private final ProductRepositoryGateway productRepositoryGateway;
    private final ProductDomainMapper productDomainMapper;


    public CreateCouponUseCaseImpl(ProductRepositoryGateway productRepositoryGateway,
            ProductDomainMapper productDomainMapper) {
        this.productRepositoryGateway = productRepositoryGateway;
        this.productDomainMapper = productDomainMapper;
    }

    @Override
    public CreateProductUseCaseOutput execute(CreateCouponUseCaseInput input) {
        Product product = productRepositoryGateway.findById(input.discount().productId())
                .orElseThrow(() -> new ApplicationException(
                        "Não foi possível criar o cupom, pois o produto com ID "
                                + input.discount().productId() + " não existe."));

        Discount discount = new Discount(null, input.discount().value());
        Coupon coupon = new Coupon(null, input.name(), input.code(), discount);

        product.addCoupon(coupon);

        Product productWithCoupon = productRepositoryGateway.update(product);
        return productDomainMapper.domainToOutput(productWithCoupon);
    }
}
