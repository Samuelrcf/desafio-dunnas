package com.dunnas.desafio.components.product.infra.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dunnas.desafio.components.product.infra.persistence.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

}
