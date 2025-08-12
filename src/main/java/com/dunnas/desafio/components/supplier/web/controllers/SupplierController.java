package com.dunnas.desafio.components.supplier.web.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dunnas.desafio.components.supplier.application.usecases.CheckHistorySupplierUseCase;
import com.dunnas.desafio.components.supplier.application.usecases.CreateSupplierUseCase;
import com.dunnas.desafio.components.supplier.application.usecases.FetchSupplierInfoUseCase;
import com.dunnas.desafio.components.supplier.application.usecases.inputs.CreateSupplierUseCaseInput;
import com.dunnas.desafio.components.supplier.application.usecases.outputs.CheckHistoryUseCaseOutput;
import com.dunnas.desafio.components.supplier.application.usecases.outputs.FetchSupplierInfoUseCaseOutput;
import com.dunnas.desafio.components.supplier.web.dtos.CreateSupplierDto;
import com.dunnas.desafio.components.supplier.web.dtos.OrderDto;
import com.dunnas.desafio.components.supplier.web.dtos.ReadSupplierDto;
import com.dunnas.desafio.components.supplier.web.mappers.SupplierDtoMapper;
import com.dunnas.desafio.components.user.web.dtos.CreateUserDto;
import com.dunnas.desafio.shared.response.PaginationResult;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/suppliers")
public class SupplierController {
	
    private final SupplierDtoMapper mapper;
    private final CreateSupplierUseCase createUseCase;
    private final FetchSupplierInfoUseCase fetchSupplierInfoUseCase;
    private final CheckHistorySupplierUseCase checkHistoryUseCase;
    
    @PostMapping("/register")
    public String registerSupplier(HttpServletRequest request, Model model) {
        try {
            CreateSupplierDto dto = new CreateSupplierDto(
                request.getParameter("name"),
                request.getParameter("cnpj"),
                new CreateUserDto(
                    request.getParameter("createUserDto.userName"),
                    request.getParameter("createUserDto.password"),
                    request.getParameter("createUserDto.role")
                )
            );

            CreateSupplierUseCaseInput input = mapper.createDtoToCreateUseCaseInput(dto);
            createUseCase.execute(input);

            model.addAttribute("successMessage", "Fornecedor cadastrado com sucesso.");
            return "login"; 
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";  
        }
    }
    
    @GetMapping("/orders")
    public String showSupplierOrdersPage() {
    	return "supplierHistory"; 
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
	
    @GetMapping("/info")
    public String fetchSupplierInfo(Model model) throws Exception {
        FetchSupplierInfoUseCaseOutput output = fetchSupplierInfoUseCase.execute();

        ReadSupplierDto readDto = mapper.fetchSupplierInfoUseCaseOutputToReadDto(output);

        model.addAttribute("supplier", readDto);

        return "supplierProfile";
    }
}
