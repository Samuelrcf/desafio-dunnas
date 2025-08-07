package com.dunnas.desafio.components.order.web.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dunnas.desafio.components.order.application.usecases.CreateOrderUseCase;
import com.dunnas.desafio.components.order.application.usecases.inputs.CreateOrderUseCaseInput;
import com.dunnas.desafio.components.order.application.usecases.outputs.CreateOrderUseCaseOutput;
import com.dunnas.desafio.components.order.web.dtos.CreateOrderDto;
import com.dunnas.desafio.components.order.web.dtos.ReadOrderDto;
import com.dunnas.desafio.components.order.web.mappers.OrderDtoMapper;
import com.dunnas.desafio.shared.response.ApiSuccessResponse;
import com.dunnas.desafio.shared.response.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
	
    private final OrderDtoMapper mapper;
    private final CreateOrderUseCase createOrderUseCase;

    @PostMapping
    public ResponseEntity<ApiSuccessResponse<List<ReadOrderDto>>> create(@Valid @RequestBody CreateOrderDto createOrderDto, HttpServletRequest request) throws Exception {

        CreateOrderUseCaseInput input = mapper.createDtoToCreateOrderUseCaseInput(createOrderDto);
        List<CreateOrderUseCaseOutput> outputs = createOrderUseCase.execute(input);
        List<ReadOrderDto> readDtos = mapper.createOrderUseCaseOutputToReadDto(outputs);

        ApiSuccessResponse<List<ReadOrderDto>> response = ResponseUtil.success(readDtos, "Pedido realizado com sucesso.", request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
