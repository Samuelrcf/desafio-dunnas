package com.dunnas.desafio.components.supplier.infra.persistence.mappers;

import java.util.ArrayList;
import java.util.List;

import com.dunnas.desafio.components.product.domain.models.Product;
import com.dunnas.desafio.components.product.infra.persistence.entities.ProductEntity;
import com.dunnas.desafio.components.product.infra.persistence.mappers.ProductEntityMapper;
import com.dunnas.desafio.components.supplier.domain.models.Supplier;
import com.dunnas.desafio.components.supplier.infra.persistence.entities.SupplierEntity;
import com.dunnas.desafio.components.user.infra.persistence.mappers.UserEntityMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SupplierEntityMapper {
	
	private final ProductEntityMapper productEntityMapper;
	private final UserEntityMapper userEntityMapper;

    public SupplierEntity modelToEntity(Supplier supplier) {
        if (supplier == null) return null;

        SupplierEntity entity = new SupplierEntity();
        entity.setId(supplier.getId());
        entity.setName(supplier.getName());
        entity.setCnpj(supplier.getCnpj());

        if (supplier.getProducts() != null) {
            List<ProductEntity> productEntities = supplier.getProducts().stream()
                .map(product -> {
                    ProductEntity pe = productEntityMapper.modelToEntity(product);
                    pe.setSupplierEntity(entity);
                    return pe;
                })
                .toList();
            entity.setProductEntities(productEntities);
        }

        entity.setUserEntity(userEntityMapper.modelToEntity(supplier.getUser()));

        return entity;
    }

    public Supplier entityToModel(SupplierEntity entity) {
        if (entity == null) return null;

        List<Product> products = new ArrayList<>();
        if (entity.getProductEntities() != null) {
            for (ProductEntity productEntity : entity.getProductEntities()) {
                Product product = productEntityMapper.entityToModel(productEntity);
                products.add(product);
            }
        }

        return new Supplier(
            entity.getId(),
            entity.getName(),
            entity.getCnpj(),
            products,
            userEntityMapper.entityToModel(entity.getUserEntity())
        );
    }

}

