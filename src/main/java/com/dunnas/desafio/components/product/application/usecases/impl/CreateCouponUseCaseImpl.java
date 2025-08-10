package com.dunnas.desafio.components.product.application.usecases.impl;

import com.dunnas.desafio.components.product.application.gateways.CouponRepositoryGateway;
import com.dunnas.desafio.components.product.application.gateways.DiscountRepositoryGateway;
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
    private final DiscountRepositoryGateway discountRepositoryGateway;
    private final CouponRepositoryGateway couponRepositoryGateway;


    public CreateCouponUseCaseImpl(ProductRepositoryGateway productRepositoryGateway,
            ProductDomainMapper productDomainMapper,
            DiscountRepositoryGateway discountRepositoryGateway,
            CouponRepositoryGateway couponRepositoryGateway) {
        this.productRepositoryGateway = productRepositoryGateway;
        this.productDomainMapper = productDomainMapper;
        this.discountRepositoryGateway = discountRepositoryGateway;
        this.couponRepositoryGateway = couponRepositoryGateway;
    }

    @Override
    public CreateProductUseCaseOutput execute(CreateCouponUseCaseInput input) {
        boolean productExists = productRepositoryGateway.existsById(input.discount().productId());
        if (!productExists) {
            throw new ApplicationException(
                    "Não foi possível criar o cupom para o produto informado, pois o produto "
                            + "não existe.");
        }

        Discount discount = new Discount(null, input.discount().value(),
                input.discount().productId());
        Coupon coupon = new Coupon(null, input.name(), input.code(), discount);
        couponRepositoryGateway.create(coupon);

        Optional<Product> productWithNewDiscount = productRepositoryGateway.findById(
                input.discount().productId());
        return productDomainMapper.domainToOutput(productWithNewDiscount.get());
    }
}
