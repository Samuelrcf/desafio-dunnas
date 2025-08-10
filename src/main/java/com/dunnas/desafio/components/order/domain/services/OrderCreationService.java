package com.dunnas.desafio.components.order.domain.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.dunnas.desafio.components.order.application.usecases.inputs.ProductQuantityInput;
import com.dunnas.desafio.components.order.domain.models.OrderItem;
import com.dunnas.desafio.components.product.domain.models.Product;
import com.dunnas.desafio.components.supplier.domain.models.Supplier;
import com.dunnas.desafio.shared.exceptions.ObjectNotFoundException;

public class OrderCreationService {

    public Map<Supplier, List<OrderItem>> groupItemsBySupplier(List<ProductQuantityInput> inputs, List<Product> products) {
        return inputs.stream()
                .collect(Collectors.groupingBy(
                        pq -> findProduct(products, pq.productId()).getSupplier(),
                        Collectors.mapping(
                                pq -> OrderItem.from(findProduct(products, pq.productId()), pq.quantity()),
                                Collectors.toList()
                        )
                ));
    }

    private Product findProduct(List<Product> products, Long productId) {
        return products.stream()
                .filter(pr -> pr.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado"));
    }
}

