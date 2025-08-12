package com.dunnas.desafio.components.user.web.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserDto {

    @NotBlank(message = "Digite o email.")
    private final String userName;
    
    @NotBlank(message = "Digite sua senha.")
    private final String password;
    
    @NotBlank(message = "Selecione sua função.")
    private final String role;

}
