package com.dunnas.desafio.components.product.application.usecases.impl;

import com.dunnas.desafio.components.product.application.gateways.CouponRepositoryGateway;
import com.dunnas.desafio.components.product.application.gateways.DiscountRepositoryGateway;
import com.dunnas.desafio.components.product.application.gateways.ProductRepositoryGateway;
import com.dunnas.desafio.components.product.application.usecases.DeleteDiscountUseCase;
import com.dunnas.desafio.shared.exceptions.ApplicationException;

public class DeleteDiscountUseCaseImpl implements DeleteDiscountUseCase {
    private final ProductRepositoryGateway productRepositoryGateway;
    private final DiscountRepositoryGateway discountRepositoryGateway;

    public DeleteDiscountUseCaseImpl(DiscountRepositoryGateway discountRepositoryGateway, ProductRepositoryGateway productRepositoryGateway) {
        this.discountRepositoryGateway = discountRepositoryGateway;
        this.productRepositoryGateway = productRepositoryGateway;
    }

    @Override
    public void execute(Long id) {
        boolean productExists = productRepositoryGateway.existsById(id);
        if (!productExists) {
            throw new ApplicationException(
                    "Não foi possível deletar o desconto para o produto informado, pois o produto "
                            + "não existe.");
        }

        discountRepositoryGateway.deleteById(id);
    }
}
