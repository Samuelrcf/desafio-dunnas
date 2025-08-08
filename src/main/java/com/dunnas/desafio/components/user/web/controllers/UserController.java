package com.dunnas.desafio.components.user.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dunnas.desafio.components.user.application.usecases.AuthenticationUseCase;
import com.dunnas.desafio.components.user.application.usecases.inputs.AuthenticationUseCaseInput;
import com.dunnas.desafio.components.user.web.dtos.LoginDto;
import com.dunnas.desafio.components.user.web.mappers.UserDtoMapper;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
	
    private final UserDtoMapper mapper;
    private final AuthenticationUseCase authenticationUseCase;

	@PostMapping("/auth/login")
	public ResponseEntity<Void> login(@RequestBody @Valid LoginDto loginDto, HttpServletResponse response) throws Exception {
		
        AuthenticationUseCaseInput input = mapper.loginDtoToAuthenticationUseCaseInput(loginDto);
        authenticationUseCase.execute(input, response);

        return ResponseEntity.ok().build();
	}
}