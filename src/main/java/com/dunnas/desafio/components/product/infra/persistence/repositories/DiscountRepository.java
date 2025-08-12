package com.dunnas.desafio.components.product.infra.persistence.repositories;

import com.dunnas.desafio.components.product.infra.persistence.entities.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, Long> {

}
