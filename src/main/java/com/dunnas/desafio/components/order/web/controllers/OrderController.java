package com.dunnas.desafio.components.order.web.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dunnas.desafio.components.order.application.usecases.CreateOrderUseCase;
import com.dunnas.desafio.components.order.application.usecases.inputs.CreateOrderUseCaseInput;
import com.dunnas.desafio.components.order.web.dtos.CreateOrderDto;
import com.dunnas.desafio.components.order.web.dtos.ProductQuantityDto;
import com.dunnas.desafio.components.order.web.mappers.OrderDtoMapper;
import com.dunnas.desafio.components.product.application.usecases.ListProductsUseCase;
import com.dunnas.desafio.components.product.application.usecases.outputs.CreateProductUseCaseOutput;
import com.dunnas.desafio.components.product.web.dtos.ReadProductDto;
import com.dunnas.desafio.components.product.web.mappers.ProductDtoMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
	
    private final OrderDtoMapper mapper;
    private final ProductDtoMapper productMapper;
    private final CreateOrderUseCase createOrderUseCase;
    private final ListProductsUseCase listProductsUseCase;

    @PostMapping("/create")
    public String createOrder(@RequestParam Map<String, String> params, Model model) throws Exception {

        List<ProductQuantityDto> productsQuantities = params.entrySet().stream()
            .filter(e -> e.getKey().startsWith("quantity_"))
            .map(e -> {
                Long productId = Long.parseLong(e.getKey().substring("quantity_".length()));
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(e.getValue());
                } catch (NumberFormatException ex) {
                    quantity = 0;
                }
                return new ProductQuantityDto(productId, quantity);
            })
            .filter(pq -> pq.getQuantity() > 0) 
            .toList();

        if (productsQuantities.isEmpty()) {
            model.addAttribute("error", "Selecione pelo menos um produto com quantidade maior que zero.");
            List<CreateProductUseCaseOutput> products = listProductsUseCase.execute();
            List<ReadProductDto> readProductDtos = productMapper.listUseCaseOutputToReadDto(products);
            model.addAttribute("products", readProductDtos);
            return "productList";
        }

        CreateOrderDto createOrderDto = new CreateOrderDto(productsQuantities);
        CreateOrderUseCaseInput input = mapper.createDtoToCreateOrderUseCaseInput(createOrderDto);

        createOrderUseCase.execute(input);

        model.addAttribute("message", "Pedido realizado com sucesso!");
        return "orderConfirmation"; 
    }
}

