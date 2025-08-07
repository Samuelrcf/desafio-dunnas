package com.dunnas.desafio.components.product.infra.persistence.mappers;

import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.product.domain.models.Product;
import com.dunnas.desafio.components.product.infra.persistence.entities.ProductEntity;
import com.dunnas.desafio.components.supplier.infra.persistence.mappers.SupplierEntityMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductEntityMapper {
	
	private final SupplierEntityMapper supplierEntityMapper;

    public ProductEntity modelToEntity(Product product) {
        if (product == null) return null;

        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setSupplierEntity(supplierEntityMapper.modelToEntity(product.getSupplier()));

        return entity;
    }

    public Product entityToModel(ProductEntity entity) {
        if (entity == null) return null;

        return new Product(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getPrice(),
            supplierEntityMapper.entityToModel(entity.getSupplierEntity())
        );
    }

}

