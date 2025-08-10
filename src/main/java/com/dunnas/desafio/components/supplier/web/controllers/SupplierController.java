package com.dunnas.desafio.components.supplier.web.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dunnas.desafio.components.supplier.application.usecases.CheckHistorySupplierUseCase;
import com.dunnas.desafio.components.supplier.application.usecases.CreateSupplierUseCase;
import com.dunnas.desafio.components.supplier.application.usecases.FetchSupplierInfoUseCase;
import com.dunnas.desafio.components.supplier.application.usecases.inputs.CreateSupplierUseCaseInput;
import com.dunnas.desafio.components.supplier.application.usecases.outputs.CheckHistoryUseCaseOutput;
import com.dunnas.desafio.components.supplier.application.usecases.outputs.CreateSupplierUseCaseOutput;
import com.dunnas.desafio.components.supplier.application.usecases.outputs.FetchSupplierInfoUseCaseOutput;
import com.dunnas.desafio.components.supplier.web.dtos.CreateSupplierDto;
import com.dunnas.desafio.components.supplier.web.dtos.OrderDto;
import com.dunnas.desafio.components.supplier.web.dtos.ReadSupplierDto;
import com.dunnas.desafio.components.supplier.web.mappers.SupplierDtoMapper;
import com.dunnas.desafio.shared.response.ApiSuccessResponse;
import com.dunnas.desafio.shared.response.PaginationResult;
import com.dunnas.desafio.shared.response.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/suppliers")
public class SupplierController {
	
    private final SupplierDtoMapper mapper;
    private final CreateSupplierUseCase createUseCase;
    private final FetchSupplierInfoUseCase fetchSupplierInfoUseCase;
    private final CheckHistorySupplierUseCase checkHistoryUseCase;

    @PostMapping
    public ResponseEntity<ApiSuccessResponse<ReadSupplierDto>> create(@Valid @RequestBody CreateSupplierDto createSupplierDto, HttpServletRequest request) throws Exception {

        CreateSupplierUseCaseInput input = mapper.createDtoToCreateUseCaseInput(createSupplierDto);
        CreateSupplierUseCaseOutput output = createUseCase.execute(input);
        ReadSupplierDto readDto = mapper.createUseCaseOutputToReadDto(output);

        ApiSuccessResponse<ReadSupplierDto> response = ResponseUtil.success(readDto, "Fornecedor cadastrado com sucesso.", request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("/history")
    public String checkHistory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) throws Exception {

        PaginationResult<CheckHistoryUseCaseOutput> output = checkHistoryUseCase.execute(page, size);

        List<OrderDto> dtoList = output.getContent().stream()
                .map(mapper::checkHistoryUseCaseOutputToOrderDto)
                .toList();

        Page<OrderDto> pageResult = new PageImpl<>(dtoList, PageRequest.of(page, size), output.getTotalElements());

        model.addAttribute("ordersPage", pageResult);

        return "supplierHistory"; 
    }
	
	@GetMapping
	public ResponseEntity<ApiSuccessResponse<ReadSupplierDto>> fetchSupplierInfo(HttpServletRequest request) throws Exception {
		
		FetchSupplierInfoUseCaseOutput output = fetchSupplierInfoUseCase.execute();
		
		ReadSupplierDto readDto = mapper.fetchSupplierInfoUseCaseOutputToReadDto(output);
		
		ApiSuccessResponse<ReadSupplierDto> response = ResponseUtil.success(readDto, "Busca bem-sucedida.",
				request.getRequestURI());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
