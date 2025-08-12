package com.dunnas.desafio.components.product.application.usecases.inputs;

public record CreateCouponUseCaseInput(String name, String code,
                                       CreateDiscountUseCaseInput discount) {

}
