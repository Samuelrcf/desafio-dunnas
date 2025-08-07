package com.dunnas.desafio.components.client.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dunnas.desafio.components.client.application.usecases.AddCreditUseCase;
import com.dunnas.desafio.components.client.application.usecases.CreateClientUseCase;
import com.dunnas.desafio.components.client.application.usecases.inputs.AddCreditUseCaseInput;
import com.dunnas.desafio.components.client.application.usecases.inputs.CreateClientUseCaseInput;
import com.dunnas.desafio.components.client.application.usecases.outputs.AddCreditUseCaseOutput;
import com.dunnas.desafio.components.client.application.usecases.outputs.CreateClientUseCaseOutput;
import com.dunnas.desafio.components.client.web.dtos.AddCreditDto;
import com.dunnas.desafio.components.client.web.dtos.CreateClientDto;
import com.dunnas.desafio.components.client.web.dtos.ReadClientDto;
import com.dunnas.desafio.components.client.web.mappers.ClientDtoMapper;
import com.dunnas.desafio.shared.response.ApiSuccessResponse;
import com.dunnas.desafio.shared.response.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {
	
    private final ClientDtoMapper mapper;
    private final CreateClientUseCase createClientUseCase;
    private final AddCreditUseCase addCreditUseCase;

    @PostMapping
    public ResponseEntity<ApiSuccessResponse<ReadClientDto>> create(@Valid @RequestBody CreateClientDto createClientDto, HttpServletRequest request) throws Exception {

        CreateClientUseCaseInput input = mapper.createDtoToCreateClientUseCaseInput(createClientDto);
        CreateClientUseCaseOutput output = createClientUseCase.execute(input);
        ReadClientDto readDto = mapper.createClientUseCaseOutputToReadDto(output);

        ApiSuccessResponse<ReadClientDto> response = ResponseUtil.success(readDto, "Cliente criado com sucesso.", request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/credit")
    public ResponseEntity<ApiSuccessResponse<ReadClientDto>> addCredit(@Valid @RequestBody AddCreditDto addCreditDto, HttpServletRequest request) throws Exception {
    	
    	AddCreditUseCaseInput input = mapper.addCreditDtoToAddCreditUseCaseInput(addCreditDto);
    	AddCreditUseCaseOutput output = addCreditUseCase.execute(input);
    	ReadClientDto readDto = mapper.addCreditUseCaseOutputToReadDto(output);
    	
    	ApiSuccessResponse<ReadClientDto> response = ResponseUtil.success(readDto, "Transação bem-sucedida.", request.getRequestURI());
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
