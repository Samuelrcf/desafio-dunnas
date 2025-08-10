package com.dunnas.desafio.components.client.web.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dunnas.desafio.components.client.application.usecases.AddCreditUseCase;
import com.dunnas.desafio.components.client.application.usecases.CheckHistoryClientUseCase;
import com.dunnas.desafio.components.client.application.usecases.CreateClientUseCase;
import com.dunnas.desafio.components.client.application.usecases.FetchClientInfoUseCase;
import com.dunnas.desafio.components.client.application.usecases.inputs.AddCreditUseCaseInput;
import com.dunnas.desafio.components.client.application.usecases.inputs.CreateClientUseCaseInput;
import com.dunnas.desafio.components.client.application.usecases.outputs.CheckHistoryUseCaseOutput;
import com.dunnas.desafio.components.client.application.usecases.outputs.FetchClientInfoUseCaseOutput;
import com.dunnas.desafio.components.client.web.dtos.AddCreditDto;
import com.dunnas.desafio.components.client.web.dtos.CreateClientDto;
import com.dunnas.desafio.components.client.web.dtos.OrderDto;
import com.dunnas.desafio.components.client.web.dtos.ReadClientDto;
import com.dunnas.desafio.components.client.web.mappers.ClientDtoMapper;
import com.dunnas.desafio.components.user.web.dtos.CreateUserDto;
import com.dunnas.desafio.shared.response.PaginationResult;

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

    @PostMapping("/register")
    public String registerClient(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            CreateClientDto dto = new CreateClientDto(
                    request.getParameter("name"),
                    request.getParameter("cpf"),
                    LocalDate.parse(request.getParameter("birthDate")),
                    new CreateUserDto(
                            request.getParameter("createUserDto.userName"),
                            request.getParameter("createUserDto.password"),
                            request.getParameter("createUserDto.role")
                    )
            );

            CreateClientUseCaseInput input = mapper.createDtoToCreateClientUseCaseInput(dto);
            createClientUseCase.execute(input);

            redirectAttributes.addFlashAttribute("successMessage", "Cliente cadastrado com sucesso.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao cadastrar cliente: " + e.getMessage());
            return "redirect:/register/error";
        }
    }

    @PostMapping("/credit")
    public String addCredit(@Valid @ModelAttribute AddCreditDto addCreditDto,
                            RedirectAttributes redirectAttributes) throws Exception {

        AddCreditUseCaseInput input = mapper.addCreditDtoToAddCreditUseCaseInput(addCreditDto);
        addCreditUseCase.execute(input);

        redirectAttributes.addFlashAttribute("successMessage", "Crédito adicionado com sucesso!");
        return "redirect:/clients/info";
    }
    
    @GetMapping("/orders")
    public String showClientOrdersPage() {
        return "clientHistory"; 
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

        return "clientHistory";
    }
	
    @GetMapping("/info")
    public String fetchClientInfo(Model model) throws Exception {
        FetchClientInfoUseCaseOutput output = fetchClientInfoUseCase.execute();
        ReadClientDto readDto = mapper.fetchClientInfoUseCaseOutputToReadDto(output);
        model.addAttribute("client", readDto);
        return "clientProfile";
    }

}
