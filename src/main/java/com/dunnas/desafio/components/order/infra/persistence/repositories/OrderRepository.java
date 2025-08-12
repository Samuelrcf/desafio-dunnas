package com.dunnas.desafio.components.order.infra.persistence.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dunnas.desafio.components.order.infra.persistence.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

	Page<OrderEntity> findByClientEntityId(Long clientId, Pageable pageable);
	Page<OrderEntity> findBySupplierEntityId(Long supplierId, Pageable pageable);
}
