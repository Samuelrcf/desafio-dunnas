package com.dunnas.desafio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dunnas.desafio.components.client.application.usecases.FetchClientInfoUseCase;
import com.dunnas.desafio.components.client.application.usecases.outputs.FetchClientInfoUseCaseOutput;
import com.dunnas.desafio.components.client.web.dtos.ReadClientDto;
import com.dunnas.desafio.components.client.web.mappers.ClientDtoMapper;

@Controller
@RequestMapping("/clients")
public class ClientProfileController {

    private final FetchClientInfoUseCase fetchClientInfoUseCase;
    private final ClientDtoMapper mapper;

    public ClientProfileController(FetchClientInfoUseCase fetchClientInfoUseCase, ClientDtoMapper mapper) {
        this.fetchClientInfoUseCase = fetchClientInfoUseCase;
        this.mapper = mapper;
    }

    @GetMapping("/info")
    public String showClientProfile(Model model) throws Exception {
        FetchClientInfoUseCaseOutput output = fetchClientInfoUseCase.execute();
        ReadClientDto readDto = mapper.fetchClientInfoUseCaseOutputToReadDto(output);
        model.addAttribute("client", readDto);
        return "profileClient"; 
    }

}

