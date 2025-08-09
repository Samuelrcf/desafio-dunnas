package com.dunnas.desafio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dunnas.desafio.components.client.application.usecases.FetchClientInfoUseCase;
import com.dunnas.desafio.components.client.application.usecases.outputs.FetchClientInfoUseCaseOutput;
import com.dunnas.desafio.components.client.web.dtos.ReadClientDto;
import com.dunnas.desafio.components.client.web.mappers.ClientDtoMapper;
import com.dunnas.desafio.components.supplier.application.usecases.FetchSupplierInfoUseCase;
import com.dunnas.desafio.components.supplier.application.usecases.outputs.FetchSupplierInfoUseCaseOutput;
import com.dunnas.desafio.components.supplier.web.dtos.ReadSupplierDto;
import com.dunnas.desafio.components.supplier.web.mappers.SupplierDtoMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class ProfileController {

    private final FetchClientInfoUseCase fetchClientInfoUseCase;
    private final ClientDtoMapper clientMapper;
    private final FetchSupplierInfoUseCase fetchSupplierInfoUseCase;
    private final SupplierDtoMapper supplierMapper;


    @GetMapping("clients/info")
    public String showClientProfile(Model model) throws Exception {
        FetchClientInfoUseCaseOutput output = fetchClientInfoUseCase.execute();
        ReadClientDto readDto = clientMapper.fetchClientInfoUseCaseOutputToReadDto(output);
        model.addAttribute("client", readDto);
        return "clientProfile"; 
    }

    @GetMapping("suppliers/info")
    public String showSupplierProfile(Model model) throws Exception {
        FetchSupplierInfoUseCaseOutput output = fetchSupplierInfoUseCase.execute();
        ReadSupplierDto readDto = supplierMapper.fetchSupplierInfoUseCaseOutputToReadDto(output);
        model.addAttribute("supplier", readDto);
        return "supplierProfile"; 
    }

}

