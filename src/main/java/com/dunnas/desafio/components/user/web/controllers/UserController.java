package com.dunnas.desafio.components.user.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dunnas.desafio.components.user.application.usecases.AuthenticationUseCase;
import com.dunnas.desafio.components.user.application.usecases.inputs.AuthenticationUseCaseInput;
import com.dunnas.desafio.components.user.domain.models.User;
import com.dunnas.desafio.components.user.web.dtos.LoginDto;
import com.dunnas.desafio.components.user.web.mappers.UserDtoMapper;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping
@AllArgsConstructor
public class UserController {
	
    private final UserDtoMapper mapper;
    private final AuthenticationUseCase authenticationUseCase;
    
    @GetMapping
    public String showForm() {
        return "register";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }
    
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; 
    }
    
    @PostMapping("/auth")
    public String login(@Valid LoginDto loginDto, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            AuthenticationUseCaseInput input = mapper.loginDtoToAuthenticationUseCaseInput(loginDto);
            User user = authenticationUseCase.execute(input, response); 

            if (user.getRole().equalsIgnoreCase("CLIENT")) {
                return "redirect:/clients/info"; 
            } else if (user.getRole().equalsIgnoreCase("SUPPLIER")) {
                return "redirect:/suppliers/info"; 
            } else {
                return "redirect:/";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Usuário ou senha inválidos");
            return "redirect:/login";
        }
    }

}