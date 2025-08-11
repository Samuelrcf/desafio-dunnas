package com.dunnas.desafio.components.order.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
@Controller
@RequestMapping("/orders")
public class OrderController {

	private final OrderDtoMapper mapper;
	private final ProductDtoMapper productMapper;
	private final CreateOrderUseCase createOrderUseCase;
	private final ListProductsUseCase listProductsUseCase;

	@PostMapping("/create")
	public String createOrder(@RequestParam List<Long> productIds, @RequestParam List<Integer> quantities,
			Model model) {
		try {

			if (productIds == null || quantities == null || productIds.isEmpty() || quantities.isEmpty()
					|| productIds.size() != quantities.size()) {
				model.addAttribute("error", "Selecione pelo menos um produto com quantidade válida.");
				List<CreateProductUseCaseOutput> products = listProductsUseCase.execute();
				List<ReadProductDto> readProductDtos = productMapper.listUseCaseOutputToReadDto(products);
				model.addAttribute("products", readProductDtos);
				return "productList";
			}

			List<ProductQuantityDto> productsQuantities = new ArrayList<>();
			for (int i = 0; i < productIds.size(); i++) {
				int qty = quantities.get(i);
				if (qty > 0) {
					productsQuantities.add(new ProductQuantityDto(productIds.get(i), qty));
				}
			}

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

			List<CreateProductUseCaseOutput> products = listProductsUseCase.execute();
			List<ReadProductDto> readProductDtos = productMapper.listUseCaseOutputToReadDto(products);
			model.addAttribute("products", readProductDtos);

			model.addAttribute("success", "Pedido realizado com sucesso!");

			return "productList";
			
		} catch (Exception e) {
			model.addAttribute("error", "Erro ao processar pedido: " + e.getMessage());
			List<CreateProductUseCaseOutput> products = listProductsUseCase.execute();
			List<ReadProductDto> readProductDtos = productMapper.listUseCaseOutputToReadDto(products);
			model.addAttribute("products", readProductDtos);

			return "productList"; 
		}
	}

}
