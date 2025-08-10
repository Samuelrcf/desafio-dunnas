package com.dunnas.desafio.components.product.application.usecases.outputs;

import com.dunnas.desafio.components.product.domain.models.Coupon;
import com.dunnas.desafio.components.product.domain.models.Discount;
import java.math.BigDecimal;
import java.util.List;

public record CreateProductUseCaseOutput(Long id, String name, String description, BigDecimal price, List<Discount> discounts, List<Coupon> coupons) {

}
