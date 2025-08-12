package com.dunnas.desafio.components.product.infra.persistence.repositories;

import com.dunnas.desafio.components.product.infra.persistence.entities.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<CouponEntity, Long> {

}
