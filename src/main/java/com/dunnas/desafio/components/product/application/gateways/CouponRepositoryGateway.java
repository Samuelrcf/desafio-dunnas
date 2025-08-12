package com.dunnas.desafio.components.product.application.gateways;

import com.dunnas.desafio.components.product.domain.models.Coupon;

public interface CouponRepositoryGateway {
    Coupon create(Coupon coupon);
    void deleteById(Long id);
    boolean existsById(Long id);
}
