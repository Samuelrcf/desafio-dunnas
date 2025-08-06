package com.dunnas.desafio.components.product.infra.persistence.mappers;

import com.dunnas.desafio.components.product.domain.models.Product;
import com.dunnas.desafio.components.product.infra.persistence.entities.ProductEntity;

public class ProductEntityMapper {

    public ProductEntity modelToEntity(Product product) {
        if (product == null) return null;

        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());

        return entity;
    }

    public Product entityToModel(ProductEntity entity) {
        if (entity == null) return null;

        return new Product(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getPrice()
        );
    }
}

