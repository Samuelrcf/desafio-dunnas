package com.dunnas.desafio.components.product.application.gateways;

import com.dunnas.desafio.components.product.domain.models.Discount;

public interface DiscountRepositoryGateway {
    Discount create(Discount discount);
    void deleteById(Long id);
    boolean existsById(Long id);
}
