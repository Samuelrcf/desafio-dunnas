package com.dunnas.desafio.components.product.infra.persistence.mappers;

import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.product.domain.models.Coupon;
import com.dunnas.desafio.components.product.domain.models.Discount;
import com.dunnas.desafio.components.product.infra.persistence.entities.CouponEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CouponEntityMapper {
    private final DiscountEntityMapper discountEntityMapper;

    public Coupon entityToModel(CouponEntity couponEntity) {
        if(couponEntity == null) {
            return null;
        }

        Discount discount = discountEntityMapper.entityToModel(couponEntity.getDiscount());
        return new Coupon(couponEntity.getId(), couponEntity.getName(), couponEntity.getCode(),
                discount);
    }

    public CouponEntity modelToEntity(Coupon coupon) {
        if(coupon == null) {
            return null;
        }

        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setId(coupon.getId());
        couponEntity.setName(coupon.getName());
        couponEntity.setCode(coupon.getCode());
        couponEntity.setDiscount(discountEntityMapper.modelToEntity(coupon.getDiscount()));

        return couponEntity;
    }
}
