package com.dunnas.desafio.components.product.application.usecases.impl;

import com.dunnas.desafio.components.product.application.gateways.DiscountRepositoryGateway;
import com.dunnas.desafio.components.product.application.usecases.DeleteDiscountUseCase;
import com.dunnas.desafio.shared.exceptions.ApplicationException;

public class DeleteDiscountUseCaseImpl implements DeleteDiscountUseCase {
    private final DiscountRepositoryGateway discountRepositoryGateway;

    public DeleteDiscountUseCaseImpl(DiscountRepositoryGateway discountRepositoryGateway) {
        this.discountRepositoryGateway = discountRepositoryGateway;
    }

    @Override
    public void execute(Long id) {
        boolean discountExists = discountRepositoryGateway.existsById(id);
        if (!discountExists) {
            throw new ApplicationException(
                    "Não foi possível deletar o desconto, pois o desconto não existe.");
        }

        discountRepositoryGateway.deleteById(id);
    }
}
