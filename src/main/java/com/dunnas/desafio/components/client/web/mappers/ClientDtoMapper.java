package com.dunnas.desafio.components.client.web.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.client.application.usecases.inputs.AddCreditUseCaseInput;
import com.dunnas.desafio.components.client.application.usecases.inputs.CreateClientUseCaseInput;
import com.dunnas.desafio.components.client.application.usecases.outputs.AddCreditUseCaseOutput;
import com.dunnas.desafio.components.client.application.usecases.outputs.CheckHistoryUseCaseOutput;
import com.dunnas.desafio.components.client.application.usecases.outputs.CreateClientUseCaseOutput;
import com.dunnas.desafio.components.client.application.usecases.outputs.FetchClientInfoUseCaseOutput;
import com.dunnas.desafio.components.client.web.dtos.AddCreditDto;
import com.dunnas.desafio.components.client.web.dtos.CreateClientDto;
import com.dunnas.desafio.components.client.web.dtos.OrderDto;
import com.dunnas.desafio.components.client.web.dtos.ProductDto;
import com.dunnas.desafio.components.client.web.dtos.ReadClientDto;
import com.dunnas.desafio.components.user.application.usecases.inputs.CreateUserInput;
import com.dunnas.desafio.components.user.web.dtos.ReadUserDto;
import com.dunnas.desafio.components.user.web.mappers.UserDtoMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ClientDtoMapper {

	private final UserDtoMapper userDtoMapper;

	public CreateClientUseCaseInput createDtoToCreateClientUseCaseInput(CreateClientDto requestDto) {

		CreateUserInput createUserInput = userDtoMapper.createUserDtoToCreateUserInput(requestDto.getCreateUserDto());

		return new CreateClientUseCaseInput(requestDto.getName(), requestDto.getCpf(), requestDto.getBirthDate(),
				createUserInput);
	}

	public ReadClientDto createClientUseCaseOutputToReadDto(CreateClientUseCaseOutput output) {
		ReadUserDto readUserDto = userDtoMapper.createUserOutputToReadUserDto(output.user());
		
		return new ReadClientDto(output.id(), output.name(), output.cpf(), output.birthDate(), output.balance(), readUserDto);
	}
	
	public ReadClientDto fetchClientInfoUseCaseOutputToReadDto(FetchClientInfoUseCaseOutput output) {
		ReadUserDto readUserDto = userDtoMapper.fetchUserOutputToReadUserDto(output.user());
		
		return new ReadClientDto(output.id(), output.name(), output.cpf(), output.birthDate(), output.balance(), readUserDto);
	}
	
	public AddCreditUseCaseInput addCreditDtoToAddCreditUseCaseInput(AddCreditDto requestDto) {

		return new AddCreditUseCaseInput(requestDto.getAmount());
	}
	

	public ReadClientDto addCreditUseCaseOutputToReadDto(AddCreditUseCaseOutput output) {
		ReadUserDto readUserDto = userDtoMapper.createUserOutputToReadUserDto(output.user());
		
		return new ReadClientDto(output.id(), output.name(), output.cpf(), output.birthDate(), output.balance(), readUserDto);
	}
	
    public OrderDto checkHistoryUseCaseOutputToOrderDto(CheckHistoryUseCaseOutput output) {
        List<ProductDto> productDtos = output.products().stream()
            .map(p -> new ProductDto(p.id(), p.name(), p.description(), p.price(), p.quantity()))
            .toList();

        return new OrderDto(
            output.orderCode(),
            output.supplierName(),
            productDtos,
            output.total(),
            output.creationDate()
        );
    }
}
