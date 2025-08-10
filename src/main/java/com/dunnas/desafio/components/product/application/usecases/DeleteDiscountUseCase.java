package com.dunnas.desafio.components.product.application.usecases;

import com.dunnas.desafio.components.product.domain.models.Coupon;

public interface DeleteDiscountUseCase {
    void execute(Long id);
}
