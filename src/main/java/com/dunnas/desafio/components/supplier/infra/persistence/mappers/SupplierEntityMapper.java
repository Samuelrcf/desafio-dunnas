package com.dunnas.desafio.components.supplier.infra.persistence.mappers;

import java.util.List;

import com.dunnas.desafio.components.product.domain.models.Product;
import com.dunnas.desafio.components.product.infra.persistence.entities.ProductEntity;
import com.dunnas.desafio.components.product.infra.persistence.mappers.ProductEntityMapper;
import com.dunnas.desafio.components.supplier.domain.models.Supplier;
import com.dunnas.desafio.components.supplier.infra.persistence.entities.SupplierEntity;
import com.dunnas.desafio.components.user.infra.persistence.mappers.UserEntityMapper;

public class SupplierEntityMapper {

    public static SupplierEntity modelToEntity(Supplier supplier) {
        if (supplier == null) return null;

        SupplierEntity entity = new SupplierEntity();
        entity.setId(supplier.getId());
        entity.setName(supplier.getName());
        entity.setCnpj(supplier.getCnpj());

        if (supplier.getProducts() != null) {
            List<ProductEntity> productEntities = supplier.getProducts().stream()
                .map(product -> {
                    ProductEntity pe = ProductEntityMapper.modelToEntity(product);
                    pe.setSupplierEntity(entity);
                    return pe;
                })
                .toList();
            entity.setProductEntities(productEntities);
        }

        entity.setUserEntity(UserEntityMapper.modelToEntity(supplier.getUser()));

        return entity;
    }

    public static Supplier entityToModel(SupplierEntity entity) {
        if (entity == null) return null;

        List<Product> products = null;
        if (entity.getProductEntities() != null) {
            products = entity.getProductEntities().stream()
                .map(ProductEntityMapper::entityToModel)
                .toList();
        }

        return new Supplier(
            entity.getId(),
            entity.getName(),
            entity.getCnpj(),
            products,
            UserEntityMapper.entityToModel(entity.getUserEntity())
        );
    }
}

