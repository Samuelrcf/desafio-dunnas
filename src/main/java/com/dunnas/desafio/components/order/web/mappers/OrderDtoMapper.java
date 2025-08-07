package com.dunnas.desafio.components.order.web.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.order.application.usecases.inputs.CreateOrderUseCaseInput;
import com.dunnas.desafio.components.order.application.usecases.inputs.ProductQuantityInput;
import com.dunnas.desafio.components.order.application.usecases.outputs.CreateOrderUseCaseOutput;
import com.dunnas.desafio.components.order.application.usecases.outputs.OrderItemOutput;
import com.dunnas.desafio.components.order.web.dtos.ClientDto;
import com.dunnas.desafio.components.order.web.dtos.CreateOrderDto;
import com.dunnas.desafio.components.order.web.dtos.OrderItemDto;
import com.dunnas.desafio.components.order.web.dtos.ProductDto;
import com.dunnas.desafio.components.order.web.dtos.ProductQuantityDto;
import com.dunnas.desafio.components.order.web.dtos.ReadOrderDto;
import com.dunnas.desafio.components.order.web.dtos.SupplierDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrderDtoMapper {

	public CreateOrderUseCaseInput createDtoToCreateOrderUseCaseInput(CreateOrderDto requestDto) {
	    List<ProductQuantityInput> inputList = new ArrayList<>();

	    for (ProductQuantityDto dto : requestDto.getOrderProductDto()) {
	    	ProductQuantityInput input = new ProductQuantityInput(dto.getProductId(), dto.getQuantity());
	        inputList.add(input);
	    }

	    return new CreateOrderUseCaseInput(inputList);
	}


	public List<ReadOrderDto> createOrderUseCaseOutputToReadDto(List<CreateOrderUseCaseOutput> outputs) {
		
		List<ReadOrderDto> readOrdersDto = new ArrayList<>();
		for(CreateOrderUseCaseOutput output : outputs) {
			
			ClientDto clientDto = new ClientDto(output.clientOutput().id(), output.clientOutput().name());
			
			SupplierDto supplierDto = new SupplierDto(output.supplierOutput().id(), output.supplierOutput().name());
			
	    	List<OrderItemDto> orderItemsDto = new ArrayList<>();
	    	for(OrderItemOutput orderOutput : output.orderItemsOutput()) {
	    		ProductDto productDto = new ProductDto(orderOutput.productOutput().id(), orderOutput.productOutput().name(), orderOutput.productOutput().price());
	    		OrderItemDto orderItemDto = new OrderItemDto(productDto, orderOutput.quantity(), orderOutput.subtotal());
	    		orderItemsDto.add(orderItemDto);
	    	}
			
			ReadOrderDto readOrderDto = new ReadOrderDto(output.orderCode(), clientDto, supplierDto, orderItemsDto, output.total(), output.creationDate());
			readOrdersDto.add(readOrderDto);
		}
		
		return readOrdersDto;
	}

}
