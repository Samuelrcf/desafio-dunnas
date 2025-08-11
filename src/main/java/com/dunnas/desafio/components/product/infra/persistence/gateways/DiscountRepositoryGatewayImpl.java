package com.dunnas.desafio.components.product.infra.persistence.gateways;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dunnas.desafio.components.product.application.gateways.DiscountRepositoryGateway;
import com.dunnas.desafio.components.product.domain.models.Discount;
import com.dunnas.desafio.components.product.infra.persistence.entities.DiscountEntity;
import com.dunnas.desafio.components.product.infra.persistence.mappers.DiscountEntityMapper;
import com.dunnas.desafio.components.product.infra.persistence.repositories.DiscountRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DiscountRepositoryGatewayImpl implements DiscountRepositoryGateway {
    private final DiscountEntityMapper mapper;
    private final DiscountRepository discountRepository;

    @Transactional
    @Override
    public Discount create(Discount discount) {
        DiscountEntity discountEntity = mapper.modelToEntity(discount);
        return mapper.entityToModel(discountRepository.save(discountEntity));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        discountRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return discountRepository.existsById(id);
    }


}
