package com.dunnas.desafio.components.supplier.application.usecases.outputs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CheckHistoryUseCaseOutput(UUID orderCode, String clientName, List<ProductOutput> products,
		BigDecimal total, LocalDateTime creationDate) {
}
