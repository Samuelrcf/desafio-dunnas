package com.dunnas.desafio.components.supplier.web.dtos;

import com.dunnas.desafio.components.user.web.dtos.ReadUserDto;

import lombok.Data;

@Data
public class ReadSupplierDto {

	private final Long id;
    private final String name;
    private final String cnpj;
    private final ReadUserDto readUserDto;
}
