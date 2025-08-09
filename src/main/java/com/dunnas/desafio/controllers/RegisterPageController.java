package com.dunnas.desafio.controllers;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dunnas.desafio.components.client.web.controllers.ClientController;
import com.dunnas.desafio.components.client.web.dtos.CreateClientDto;
import com.dunnas.desafio.components.supplier.web.controllers.SupplierController;
import com.dunnas.desafio.components.supplier.web.dtos.CreateSupplierDto;
import com.dunnas.desafio.components.user.web.dtos.CreateUserDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/register")
public class RegisterPageController {

    private final ClientController clientController;
    private final SupplierController supplierController;

    public RegisterPageController(ClientController clientController, SupplierController supplierController) {
        this.clientController = clientController;
        this.supplierController = supplierController;
    }

    @GetMapping
    public String showForm(Model model) {
        return "register";
    }

    @PostMapping("/client")
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

            clientController.create(dto, request); 
            redirectAttributes.addFlashAttribute("successMessage", "Cliente cadastrado com sucesso.");
            return "redirect:/register/success";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao cadastrar cliente: " + e.getMessage());
            return "redirect:/register/error";
        }
    }

    @PostMapping("/supplier")
    public String registerSupplier(HttpServletRequest request, RedirectAttributes redirectAttributes) {
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

            supplierController.create(dto, request);
            redirectAttributes.addFlashAttribute("successMessage", "Fornecedor cadastrado com sucesso.");
            return "redirect:/register/success";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao cadastrar fornecedor: " + e.getMessage());
            return "redirect:/register/error";
        }
    }

    @GetMapping("/success")
    public String successPage() {
        return "success";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }
}
