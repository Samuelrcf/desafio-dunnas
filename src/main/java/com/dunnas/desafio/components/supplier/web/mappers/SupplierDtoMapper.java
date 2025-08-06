package com.dunnas.desafio.components.supplier.web.mappers;

import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.supplier.application.usecases.inputs.CreateSupplierUseCaseInput;
import com.dunnas.desafio.components.supplier.application.usecases.outputs.CreateSupplierUseCaseOutput;
import com.dunnas.desafio.components.supplier.web.dtos.CreateSupplierDto;
import com.dunnas.desafio.components.supplier.web.dtos.ReadSupplierDto;
import com.dunnas.desafio.components.user.application.usecases.inputs.CreateUserInput;
import com.dunnas.desafio.components.user.web.dtos.ReadUserDto;
import com.dunnas.desafio.components.user.web.mappers.UserDtoMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SupplierDtoMapper {

	private final UserDtoMapper userDtoMapper;

	public CreateSupplierUseCaseInput createDtoToCreateUseCaseInput(CreateSupplierDto requestDto) {

		CreateUserInput createUserInput = userDtoMapper.createUserDtoToCreateUserInput(requestDto.getCreateUserDto());

		return new CreateSupplierUseCaseInput(requestDto.getName(), requestDto.getCnpj(), createUserInput);
	}

	public ReadSupplierDto createUseCaseOutputToReadDto(CreateSupplierUseCaseOutput output) {
		ReadUserDto readUserDto = userDtoMapper.createUserOutputToReadUserDto(output.user());

		return new ReadSupplierDto(output.id(), output.name(), output.cnpj(), readUserDto);
	}
}
