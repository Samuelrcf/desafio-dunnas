package com.dunnas.desafio.components.order.application.usecases.outputs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreateOrderUseCaseOutput(UUID orderCode, ClientOutput clientOutput, SupplierOutput supplierOutput, List<OrderItemOutput> orderItemsOutput, BigDecimal total, LocalDateTime creationDate) {

}
