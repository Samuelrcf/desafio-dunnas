package com.dunnas.desafio.components.client.web.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.dunnas.desafio.components.user.web.dtos.ReadUserDto;

import lombok.Data;

@Data
public class ReadClientDto {

	private final Long id;
    private final String name;
    private final String cpf;
    private final LocalDate birthDate;
    private final BigDecimal balance;
    private final ReadUserDto readUserDto;
}
