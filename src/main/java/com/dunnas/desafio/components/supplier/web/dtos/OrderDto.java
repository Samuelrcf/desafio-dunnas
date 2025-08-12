package com.dunnas.desafio.components.supplier.web.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderDto {

    private final UUID orderCode;
    private final String clientName;
    private final List<ProductDto> products;
    private final BigDecimal total;
    private final LocalDateTime creationDate;
}
