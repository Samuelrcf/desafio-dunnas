package com.dunnas.desafio.components.product.infra.persistence.gateways;

import com.dunnas.desafio.components.product.application.gateways.DiscountRepositoryGateway;
import com.dunnas.desafio.components.product.domain.models.Discount;
import com.dunnas.desafio.components.product.infra.persistence.entities.DiscountEntity;
import com.dunnas.desafio.components.product.infra.persistence.mappers.DiscountEntityMapper;
import com.dunnas.desafio.components.product.infra.persistence.repositories.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class DiscountRepositoryGatewayImpl implements DiscountRepositoryGateway {
    private final DiscountRepository riscountRepository;
    private final DiscountEntityMapper mapper;
    private final DiscountRepository discountRepository;

    @Transactional
    @Override
    public Discount create(Discount discount) {
        DiscountEntity discountEntity = mapper.modelToEntity(discount);
        return mapper.entityToModel(riscountRepository.save(discountEntity));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        discountRepository.deleteById(id);
    }
}
