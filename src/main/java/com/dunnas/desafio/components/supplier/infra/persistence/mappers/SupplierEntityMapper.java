package com.dunnas.desafio.components.supplier.infra.persistence.mappers;

import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.supplier.domain.models.Supplier;
import com.dunnas.desafio.components.supplier.infra.persistence.entities.SupplierEntity;
import com.dunnas.desafio.components.user.infra.persistence.mappers.UserEntityMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SupplierEntityMapper {
	
	private final UserEntityMapper userEntityMapper;

    public SupplierEntity modelToEntity(Supplier supplier) {
        if (supplier == null) return null;

        SupplierEntity entity = new SupplierEntity();
        entity.setId(supplier.getId());
        entity.setName(supplier.getName());
        entity.setCnpj(supplier.getCnpj());
        entity.setUserEntity(userEntityMapper.modelToEntity(supplier.getUser()));

        return entity;
    }

    public Supplier entityToModel(SupplierEntity entity) {
        if (entity == null) return null;
        return new Supplier(
            entity.getId(),
            entity.getName(),
            entity.getCnpj(),
            userEntityMapper.entityToModel(entity.getUserEntity())
        );
    }

}

