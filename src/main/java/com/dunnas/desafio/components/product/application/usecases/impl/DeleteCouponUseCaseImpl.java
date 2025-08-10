package com.dunnas.desafio.components.product.application.usecases.impl;

import com.dunnas.desafio.components.product.application.gateways.CouponRepositoryGateway;
import com.dunnas.desafio.components.product.application.gateways.DiscountRepositoryGateway;
import com.dunnas.desafio.components.product.application.gateways.ProductRepositoryGateway;
import com.dunnas.desafio.components.product.application.usecases.DeleteCouponUseCase;
import com.dunnas.desafio.components.product.application.usecases.DeleteDiscountUseCase;
import com.dunnas.desafio.shared.exceptions.ApplicationException;

public class DeleteCouponUseCaseImpl implements DeleteCouponUseCase {
    private final ProductRepositoryGateway productRepositoryGateway;
    private final CouponRepositoryGateway couponRepositoryGateway;

    public DeleteCouponUseCaseImpl(CouponRepositoryGateway couponRepositoryGateway, ProductRepositoryGateway productRepositoryGateway) {
        this.couponRepositoryGateway = couponRepositoryGateway;
        this.productRepositoryGateway = productRepositoryGateway;
    }

    @Override
    public void execute(Long id) {
        boolean productExists = productRepositoryGateway.existsById(id);
        if (!productExists) {
            throw new ApplicationException(
                    "Não foi possível deletar o cupom para o produto informado, pois o produto "
                            + "não existe.");
        }

        couponRepositoryGateway.deleteById(id);
    }
}
