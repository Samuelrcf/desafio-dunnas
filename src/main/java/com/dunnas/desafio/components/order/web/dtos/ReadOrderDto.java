package com.dunnas.desafio.components.order.web.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ReadOrderDto {

	private final UUID orderCode;
    private final ClientDto readClientDto;
    private final SupplierDto readSupplierDto;
    private final List<OrderItemDto> readProductsDto;
    private final BigDecimal total;
    private final LocalDateTime creationDate;
}
