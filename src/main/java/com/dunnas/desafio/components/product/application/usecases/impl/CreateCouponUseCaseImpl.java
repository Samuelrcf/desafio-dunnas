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
import java.util.Optional;

public class CreateCouponUseCaseImpl implements CreateCouponUseCase {

    private final ProductRepositoryGateway productRepositoryGateway;
    private final ProductDomainMapper productDomainMapper;

    public CreateCouponUseCaseImpl(ProductRepositoryGateway productRepositoryGateway, ProductDomainMapper productDomainMapper) {
        this.productRepositoryGateway = productRepositoryGateway;
        this.productDomainMapper = productDomainMapper;
    }

    @Override
    public CreateProductUseCaseOutput execute(CreateCouponUseCaseInput input) {
        Optional<Product> foundProduct = productRepositoryGateway.findById(input.discount().productId());

        if (foundProduct.isEmpty()) {
            throw new ApplicationException(
                    "Não foi possível criar o desconto para o produto informado, pois o produto "
                            + "não existe.");
        }

        Discount discount = new Discount(null, input.discount().value(), foundProduct.get().getId());
        Coupon coupon = new Coupon(null, input.name(), input.code(), discount);

        foundProduct.get().addCoupon(coupon);

        Product updateProductWithDiscount = productRepositoryGateway.update(foundProduct.get());
        return productDomainMapper.domainToOutput(updateProductWithDiscount);
    }
}
