package com.dunnas.desafio.components.supplier.application.gateways;

import java.util.Optional;

import com.dunnas.desafio.components.supplier.domain.models.Supplier;

public interface SupplierRepositoryGateway {

    Supplier create(Supplier Supplier);

    Supplier update(Supplier Supplier);

    Optional<Supplier> findById(Long id);

    Optional<Supplier> findByName(String name);

    boolean existsById(Long id);

    void deleteById(Long id);
}
