package com.dunnas.desafio.components.supplier.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dunnas.desafio.components.supplier.application.usecases.CreateSupplierUseCase;
import com.dunnas.desafio.components.supplier.application.usecases.inputs.CreateSupplierUseCaseInput;
import com.dunnas.desafio.components.supplier.application.usecases.outputs.CreateSupplierUseCaseOutput;
import com.dunnas.desafio.components.supplier.web.dtos.CreateSupplierDto;
import com.dunnas.desafio.components.supplier.web.dtos.ReadSupplierDto;
import com.dunnas.desafio.components.supplier.web.mappers.SupplierDtoMapper;
import com.dunnas.desafio.shared.response.ApiSuccessResponse;
import com.dunnas.desafio.shared.response.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/suppliers")
public class SupplierController {
	
    private final SupplierDtoMapper mapper;
    private final CreateSupplierUseCase createUseCase;

    @PostMapping
    public ResponseEntity<ApiSuccessResponse<ReadSupplierDto>> create(@Valid @RequestBody CreateSupplierDto createSupplierDto, HttpServletRequest request) throws Exception {

        CreateSupplierUseCaseInput input = mapper.createDtoToCreateUseCaseInput(createSupplierDto);
        CreateSupplierUseCaseOutput output = createUseCase.execute(input);
        ReadSupplierDto readDto = mapper.createUseCaseOutputToReadDto(output);

        ApiSuccessResponse<ReadSupplierDto> response = ResponseUtil.success(readDto, "Fornecedor criado com sucesso.", request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
