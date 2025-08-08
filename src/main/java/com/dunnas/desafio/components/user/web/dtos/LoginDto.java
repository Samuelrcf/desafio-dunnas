package com.dunnas.desafio.components.user.web.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {

	@NotBlank(message = "Digite o nome de usuário.")
	private String userName;
	@NotBlank(message = "Digite a sua senha.")
	private String password;
}
