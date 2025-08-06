package com.dunnas.desafio.components.user.web.dtos;

import lombok.Data;

@Data
public class ReadUserDto {

	private final Long id;
    private final String userName;
    private final String role;
}
