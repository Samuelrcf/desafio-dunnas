package com.dunnas.desafio.components.product.application.usecases.impl;

import com.dunnas.desafio.components.product.application.gateways.CouponRepositoryGateway;
import com.dunnas.desafio.components.product.application.usecases.DeleteCouponUseCase;
import com.dunnas.desafio.shared.exceptions.ApplicationException;

public class DeleteCouponUseCaseImpl implements DeleteCouponUseCase {

    private final CouponRepositoryGateway couponRepositoryGateway;

    public DeleteCouponUseCaseImpl(CouponRepositoryGateway couponRepositoryGateway) {
        this.couponRepositoryGateway = couponRepositoryGateway;
    }

    @Override
    public void execute(Long id) {
        boolean couponExists = couponRepositoryGateway.existsById(id);
        if (!couponExists) {
            throw new ApplicationException(
                    "Não foi possível deletar o cupom para o produto informado, pois o cupom não existe ");
        }

        couponRepositoryGateway.deleteById(id);
    }
}
