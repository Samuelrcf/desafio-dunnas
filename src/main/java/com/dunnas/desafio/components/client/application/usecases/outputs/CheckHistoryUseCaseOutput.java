package com.dunnas.desafio.components.client.application.usecases.outputs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CheckHistoryUseCaseOutput(UUID orderCode, String supplierName, List<ProductOutput> products,
		BigDecimal total, LocalDateTime creationDate) {
}
