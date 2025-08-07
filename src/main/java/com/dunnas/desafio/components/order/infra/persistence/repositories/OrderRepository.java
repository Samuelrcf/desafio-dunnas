package com.dunnas.desafio.components.order.infra.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dunnas.desafio.components.order.infra.persistence.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

}
