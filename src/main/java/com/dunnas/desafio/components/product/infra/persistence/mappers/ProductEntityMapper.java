package com.dunnas.desafio.components.product.infra.persistence.mappers;

import com.dunnas.desafio.components.product.domain.models.Coupon;
import com.dunnas.desafio.components.product.domain.models.Discount;
import com.dunnas.desafio.components.product.infra.persistence.entities.CouponEntity;
import com.dunnas.desafio.components.product.infra.persistence.entities.DiscountEntity;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.product.domain.models.Product;
import com.dunnas.desafio.components.product.infra.persistence.entities.ProductEntity;
import com.dunnas.desafio.components.supplier.infra.persistence.mappers.SupplierEntityMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductEntityMapper {

    private final SupplierEntityMapper supplierEntityMapper;
    private final DiscountEntityMapper discountEntityMapper;
    private final CouponEntityMapper couponEntityMapper;

    public ProductEntity modelToEntity(Product product) {
        if (product == null) {
            return null;
        }

        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setDeleted(false);
        entity.setSupplierEntity(supplierEntityMapper.modelToEntity(product.getSupplier()));

        if (product.getDiscounts() != null) {
            if (entity.getDiscountEntities() != null) {
                for (Discount discount : product.getDiscounts()) {
                    entity.getDiscountEntities().add(discountEntityMapper.modelToEntity(discount));
                }
            }
        }

        if (product.getCoupons() != null) {
            if (entity.getDiscountEntities() != null) {
                for (Coupon coupon : product.getCoupons()) {

                    entity.getCouponEntities().add(couponEntityMapper.modelToEntity(coupon));
                }
            }
        }

        return entity;
    }

    public Product entityToModel(ProductEntity entity) {
        if (entity == null) {
            return null;
        }

        List<Discount> discounts = new ArrayList<>();
        if (entity.getDiscountEntities() != null) {
            for (DiscountEntity discountEntity : entity.getDiscountEntities()) {
                discounts.add(discountEntityMapper.entityToModel(discountEntity));
            }
        }

        List<Coupon> coupons = new ArrayList<>();
        if (entity.getCouponEntities() != null) {
            for (CouponEntity couponEntity : entity.getCouponEntities()) {
                coupons.add(couponEntityMapper.entityToModel(couponEntity));
            }
        }

        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                supplierEntityMapper.entityToModel(entity.getSupplierEntity()),
                discounts,
                coupons
        );
    }

    public List<ProductEntity> modelListToEntityList(List<Product> products) {
        List<ProductEntity> productEntities = new ArrayList<>();
        for (Product product : products) {
            ProductEntity productEntity = modelToEntity(product);
            productEntities.add(productEntity);
        }

        return productEntities;
    }

    public List<Product> entityListToModelList(List<ProductEntity> productsEntities) {
        List<Product> products = new ArrayList<>();
        for (ProductEntity product : productsEntities) {
            Product productEntity = entityToModel(product);
            products.add(productEntity);
        }

        return products;
    }

}