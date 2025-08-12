package com.dunnas.desafio.components.supplier.web.dtos;

import com.dunnas.desafio.components.user.web.dtos.CreateUserDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateSupplierDto {
	
    @NotBlank(message = "Digite seu nome completo.")
    private final String name;
    
    @NotBlank(message = "Digite seu cnpj.")
    private final String cnpj;

    @Valid
    @NotNull(message = "Dados de usuário obrigatórios.")
    private final CreateUserDto createUserDto;
}
