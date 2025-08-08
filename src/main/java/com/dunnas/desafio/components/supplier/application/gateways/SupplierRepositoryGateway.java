package com.dunnas.desafio.components.supplier.application.gateways;

import java.util.Optional;

import com.dunnas.desafio.components.order.domain.models.Order;
import com.dunnas.desafio.components.supplier.domain.models.Supplier;
import com.dunnas.desafio.shared.response.PaginationResult;

public interface SupplierRepositoryGateway {

    Supplier create(Supplier Supplier);

    Optional<Supplier> findById(Long id);

    Optional<Supplier> findByCnpj(String cnpj);

    boolean existsById(Long id);
    
    PaginationResult<Order> getHistory(Long supplierId, int page, int size);

}
