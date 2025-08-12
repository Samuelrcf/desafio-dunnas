package com.dunnas.desafio.components.product.infra.persistence.mappers;

import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.product.domain.models.Discount;
import com.dunnas.desafio.components.product.infra.persistence.entities.DiscountEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DiscountEntityMapper {
    public Discount entityToModel(DiscountEntity discountEntity) {
        if(discountEntity == null) {
            return null;
        }

        return new Discount(discountEntity.getId(), discountEntity.getValue());
    }

    public DiscountEntity modelToEntity(Discount discount) {
        if(discount == null) {
            return null;
        }

        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.setId(discount.getId());
        discountEntity.setValue(discount.getValue());
        discountEntity.setDeleted(false);

        return discountEntity;
    }
}
