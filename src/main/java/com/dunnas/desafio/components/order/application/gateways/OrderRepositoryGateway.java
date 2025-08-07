package com.dunnas.desafio.components.order.application.gateways;

import java.util.Optional;

import com.dunnas.desafio.components.order.domain.models.Order;

public interface OrderRepositoryGateway {

    Order create(Order order);

    Optional<Order> findById(Long id);

    boolean existsById(Long id);

}
