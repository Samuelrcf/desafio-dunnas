package com.dunnas.desafio.components.client.web.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dunnas.desafio.components.client.application.usecases.AddCreditUseCase;
import com.dunnas.desafio.components.client.application.usecases.CheckHistoryClientUseCase;
import com.dunnas.desafio.components.client.application.usecases.CreateClientUseCase;
import com.dunnas.desafio.components.client.application.usecases.FetchClientInfoUseCase;
import com.dunnas.desafio.components.client.application.usecases.inputs.AddCreditUseCaseInput;
import com.dunnas.desafio.components.client.application.usecases.inputs.CreateClientUseCaseInput;
import com.dunnas.desafio.components.client.application.usecases.outputs.AddCreditUseCaseOutput;
import com.dunnas.desafio.components.client.application.usecases.outputs.CheckHistoryUseCaseOutput;
import com.dunnas.desafio.components.client.application.usecases.outputs.CreateClientUseCaseOutput;
import com.dunnas.desafio.components.client.application.usecases.outputs.FetchClientInfoUseCaseOutput;
import com.dunnas.desafio.components.client.web.dtos.AddCreditDto;
import com.dunnas.desafio.components.client.web.dtos.CreateClientDto;
import com.dunnas.desafio.components.client.web.dtos.OrderDto;
import com.dunnas.desafio.components.client.web.dtos.ReadClientDto;
import com.dunnas.desafio.components.client.web.mappers.ClientDtoMapper;
import com.dunnas.desafio.shared.response.ApiSuccessResponse;
import com.dunnas.desafio.shared.response.PaginationResult;
import com.dunnas.desafio.shared.response.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/clients")
public class ClientController {

	private final ClientDtoMapper mapper;
	private final CreateClientUseCase createClientUseCase;
	private final FetchClientInfoUseCase fetchClientInfoUseCase;
	private final AddCreditUseCase addCreditUseCase;
	private final CheckHistoryClientUseCase checkHistoryUseCase;

	@PostMapping
	public ResponseEntity<ApiSuccessResponse<ReadClientDto>> create(@Valid @RequestBody CreateClientDto createClientDto,
			HttpServletRequest request) throws Exception {

		CreateClientUseCaseInput input = mapper.createDtoToCreateClientUseCaseInput(createClientDto);
		CreateClientUseCaseOutput output = createClientUseCase.execute(input);
		ReadClientDto readDto = mapper.createClientUseCaseOutputToReadDto(output);

		ApiSuccessResponse<ReadClientDto> response = ResponseUtil.success(readDto, "Cliente criado com sucesso.",
				request.getRequestURI());
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/credit")
	public ResponseEntity<ApiSuccessResponse<ReadClientDto>> addCredit(@Valid @RequestBody AddCreditDto addCreditDto,
			HttpServletRequest request) throws Exception {

		AddCreditUseCaseInput input = mapper.addCreditDtoToAddCreditUseCaseInput(addCreditDto);
		AddCreditUseCaseOutput output = addCreditUseCase.execute(input);
		ReadClientDto readDto = mapper.addCreditUseCaseOutputToReadDto(output);

		ApiSuccessResponse<ReadClientDto> response = ResponseUtil.success(readDto, "Transação bem-sucedida.",
				request.getRequestURI());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/history")
	public ResponseEntity<ApiSuccessResponse<Page<OrderDto>>> checkHistory(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, HttpServletRequest request) throws Exception {

		PaginationResult<CheckHistoryUseCaseOutput> output = checkHistoryUseCase.execute(page, size);

		List<OrderDto> dtoList = output.getContent().stream().map(mapper::checkHistoryUseCaseOutputToOrderDto).toList();

		Page<OrderDto> pageResult = new PageImpl<>(dtoList, PageRequest.of(page, size), output.getTotalElements());

		ApiSuccessResponse<Page<OrderDto>> response = new ApiSuccessResponse<>("Histórico carregado com sucesso",
				pageResult, System.currentTimeMillis(), request.getRequestURI());

		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<ApiSuccessResponse<ReadClientDto>> fetchClientInfo(HttpServletRequest request) throws Exception {
		
		FetchClientInfoUseCaseOutput output = fetchClientInfoUseCase.execute();
		
		ReadClientDto readDto = mapper.fetchClientInfoUseCaseOutputToReadDto(output);
		
		ApiSuccessResponse<ReadClientDto> response = ResponseUtil.success(readDto, "Busca bem-sucedida.",
				request.getRequestURI());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
