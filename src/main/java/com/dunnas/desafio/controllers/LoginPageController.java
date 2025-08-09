package com.dunnas.desafio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPageController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; 
    }

}

