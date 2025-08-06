package com.dunnas.desafio.components.client.web.dtos;

import java.util.Date;

import com.dunnas.desafio.components.user.web.dtos.CreateUserDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateClientDto {

    @NotBlank(message = "Digite seu nome completo.")
    private final String name;
    
    @NotBlank(message = "Digite seu cpf.")
    private final String cpf;
    
    @NotNull(message = "Selecione sua data de nascimento.")
    private final Date birthDate;
    
    @Valid
    @NotNull(message = "Dados de usuário obrigatórios.")
    private final CreateUserDto createUserDto;
    
}
