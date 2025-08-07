package com.dunnas.desafio.components.product.web.mappers;

import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.product.application.usecases.inputs.CreateProductUseCaseInput;
import com.dunnas.desafio.components.product.application.usecases.outputs.CreateProductUseCaseOutput;
import com.dunnas.desafio.components.product.web.dtos.CreateProductDto;
import com.dunnas.desafio.components.product.web.dtos.ReadProductDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductDtoMapper {

	public CreateProductUseCaseInput createDtoToCreateUseCaseInput(CreateProductDto requestDto) {

		return new CreateProductUseCaseInput(requestDto.getName(), requestDto.getDescription(), requestDto.getPrice());
	}

	public ReadProductDto createUseCaseOutputToReadDto(CreateProductUseCaseOutput output) {
		
		return new ReadProductDto(output.id(), output.name(), output.description(), output.price());
	}
}
