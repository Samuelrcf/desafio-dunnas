package com.dunnas.desafio.components.client.web.dtos;

import java.time.LocalDate;

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
    private final LocalDate birthDate;
    
    @Valid
    @NotNull(message = "Dados de usuário obrigatórios.")
    private final CreateUserDto createUserDto;
    
}
