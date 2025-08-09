package com.dunnas.desafio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dunnas.desafio.components.supplier.application.usecases.FetchSupplierInfoUseCase;
import com.dunnas.desafio.components.supplier.application.usecases.outputs.FetchSupplierInfoUseCaseOutput;
import com.dunnas.desafio.components.supplier.web.dtos.ReadSupplierDto;
import com.dunnas.desafio.components.supplier.web.mappers.SupplierDtoMapper;

@Controller
@RequestMapping("/suppliers")
public class SupplierProfileController {

    private final FetchSupplierInfoUseCase fetchSupplierInfoUseCase;
    private final SupplierDtoMapper mapper;

    public SupplierProfileController(FetchSupplierInfoUseCase fetchSupplierInfoUseCase, SupplierDtoMapper mapper) {
        this.fetchSupplierInfoUseCase = fetchSupplierInfoUseCase;
        this.mapper = mapper;
    }

    @GetMapping("/info")
    public String showSupplierProfile(Model model) throws Exception {
        FetchSupplierInfoUseCaseOutput output = fetchSupplierInfoUseCase.execute();
        ReadSupplierDto readDto = mapper.fetchSupplierInfoUseCaseOutputToReadDto(output);
        model.addAttribute("supplier", readDto);
        return "profileSupplier"; 
    }
}

