package com.dunnas.desafio.components.order.application.usecases.inputs;

public record ProductQuantityInput(Long productId, int quantity, boolean applyCoupon) {}

