package com.dunnas.desafio.components.product.infra.persistence.mappers;

import com.dunnas.desafio.components.product.domain.models.Discount;
import com.dunnas.desafio.components.product.infra.persistence.entities.DiscountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DiscountEntityMapper {
    public Discount entityToModel(DiscountEntity discountEntity) {
        return new Discount(discountEntity.getId(), discountEntity.getValue(), discountEntity.getProductEntity().getId());
    }

    public DiscountEntity modelToEntity(Discount discount) {
        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.setId(discount.getId());
        discountEntity.setValue(discount.getValue());
        discountEntity.setProductEntity(null);

        return discountEntity;
    }
}
