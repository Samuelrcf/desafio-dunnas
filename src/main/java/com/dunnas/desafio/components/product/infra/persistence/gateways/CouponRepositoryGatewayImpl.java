package com.dunnas.desafio.components.product.infra.persistence.gateways;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dunnas.desafio.components.product.application.gateways.CouponRepositoryGateway;
import com.dunnas.desafio.components.product.domain.models.Coupon;
import com.dunnas.desafio.components.product.infra.persistence.entities.CouponEntity;
import com.dunnas.desafio.components.product.infra.persistence.mappers.CouponEntityMapper;
import com.dunnas.desafio.components.product.infra.persistence.repositories.CouponRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CouponRepositoryGatewayImpl implements CouponRepositoryGateway {
    private final CouponRepository couponRepository;
    private final CouponEntityMapper couponEntityMapper;

    @Transactional
    @Override
    public Coupon create(Coupon coupon) {
        CouponEntity couponEntity = couponEntityMapper.modelToEntity(coupon);
        return couponEntityMapper.entityToModel(couponRepository.save(couponEntity));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        couponRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return couponRepository.existsById(id);
    }
}
