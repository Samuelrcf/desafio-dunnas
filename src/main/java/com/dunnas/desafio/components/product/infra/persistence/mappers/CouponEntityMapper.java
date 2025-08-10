package com.dunnas.desafio.components.product.infra.persistence.mappers;

import com.dunnas.desafio.components.product.domain.models.Coupon;
import com.dunnas.desafio.components.product.domain.models.Discount;
import com.dunnas.desafio.components.product.infra.persistence.entities.CouponEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CouponEntityMapper {
    private final DiscountEntityMapper discountEntityMapper;

    public Coupon entityToModel(CouponEntity couponEntity) {
        Discount discount = discountEntityMapper.entityToModel(couponEntity.getDiscount());
        return new Coupon(couponEntity.getId(), couponEntity.getName(), couponEntity.getCode(),
                discount);
    }

    public CouponEntity modelToEntity(Coupon coupon) {
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setId(coupon.getId());
        couponEntity.setName(coupon.getName());
        couponEntity.setCode(coupon.getCode());
        couponEntity.setDiscount(discountEntityMapper.modelToEntity(coupon.getDiscount()));
        couponEntity.setProductEntity(couponEntity.getDiscount().getProductEntity());

        return couponEntity;
    }
}
