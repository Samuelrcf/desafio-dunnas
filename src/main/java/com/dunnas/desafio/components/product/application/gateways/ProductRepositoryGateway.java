package com.dunnas.desafio.components.product.application.gateways;

import java.util.List;
import java.util.Optional;

import com.dunnas.desafio.components.product.domain.models.Product;

public interface ProductRepositoryGateway {

    Product create(Product Product);
    
    void delete(Long id);

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    boolean existsById(Long id);
    
    List<Product> findAllById(List<Long> ids);

    List<Product> findAll();
    
    List<Product> findAllBySupplierId(Long id);

}
