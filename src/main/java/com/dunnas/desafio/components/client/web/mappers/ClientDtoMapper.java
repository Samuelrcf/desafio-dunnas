package com.dunnas.desafio.components.client.web.mappers;

import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.client.application.usecases.input.CreateClientUseCaseInput;
import com.dunnas.desafio.components.client.application.usecases.outputs.CreateClientUseCaseOutput;
import com.dunnas.desafio.components.client.web.dtos.CreateClientDto;
import com.dunnas.desafio.components.client.web.dtos.ReadClientDto;
import com.dunnas.desafio.components.user.application.usecases.inputs.CreateUserInput;
import com.dunnas.desafio.components.user.web.dtos.ReadUserDto;
import com.dunnas.desafio.components.user.web.mappers.UserDtoMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ClientDtoMapper {

	private final UserDtoMapper userDtoMapper;

	public CreateClientUseCaseInput createDtoToCreateUseCaseInput(CreateClientDto requestDto) {

		CreateUserInput createUserInput = userDtoMapper.createUserDtoToCreateUserInput(requestDto.getCreateUserDto());

		return new CreateClientUseCaseInput(requestDto.getName(), requestDto.getCpf(), requestDto.getBirthDate(),
				createUserInput);
	}

	public ReadClientDto createUseCaseOutputToReadDto(CreateClientUseCaseOutput output) {
		ReadUserDto readUserDto = userDtoMapper.createUserOutputToReadUserDto(output.user());
		
		return new ReadClientDto(output.id(), output.name(), output.cpf(), output.birthDate(), output.balance(), readUserDto);
	}
}
