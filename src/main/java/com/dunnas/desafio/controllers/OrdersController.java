package com.dunnas.desafio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrdersController {

    @GetMapping("/clients/orders")
    public String showClientOrdersPage() {
        return "clientHistory"; 
    }
    
    @GetMapping("/suppliers/orders")
    public String showSupplierOrdersPage() {
    	return "supplierHistory"; 
    }
}

