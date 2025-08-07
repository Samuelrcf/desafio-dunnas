package com.dunnas.desafio.components.product.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dunnas.desafio.components.product.application.usecases.CreateProductUseCase;
import com.dunnas.desafio.components.product.application.usecases.inputs.CreateProductUseCaseInput;
import com.dunnas.desafio.components.product.application.usecases.outputs.CreateProductUseCaseOutput;
import com.dunnas.desafio.components.product.web.dtos.CreateProductDto;
import com.dunnas.desafio.components.product.web.dtos.ReadProductDto;
import com.dunnas.desafio.components.product.web.mappers.ProductDtoMapper;
import com.dunnas.desafio.shared.response.ApiSuccessResponse;
import com.dunnas.desafio.shared.response.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
	
    private final ProductDtoMapper mapper;
    private final CreateProductUseCase createUseCase;

    @PostMapping
    public ResponseEntity<ApiSuccessResponse<ReadProductDto>> create(@Valid @RequestBody CreateProductDto createProductDto, HttpServletRequest request) throws Exception {

        CreateProductUseCaseInput input = mapper.createDtoToCreateUseCaseInput(createProductDto);
        CreateProductUseCaseOutput output = createUseCase.execute(input);
        ReadProductDto readDto = mapper.createUseCaseOutputToReadDto(output);

        ApiSuccessResponse<ReadProductDto> response = ResponseUtil.success(readDto, "Produto criado com sucesso.", request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
