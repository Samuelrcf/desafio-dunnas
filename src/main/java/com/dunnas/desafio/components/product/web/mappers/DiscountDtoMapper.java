package com.dunnas.desafio.components.product.web.mappers;

import com.dunnas.desafio.components.product.application.usecases.inputs.CreateDiscountUseCaseInput;
import com.dunnas.desafio.components.product.domain.models.Discount;
import com.dunnas.desafio.components.product.web.dtos.CreateDiscountDto;
import com.dunnas.desafio.components.product.web.dtos.ReadDiscountDto;
import org.springframework.stereotype.Component;

@Component
public class DiscountDtoMapper {
    public CreateDiscountUseCaseInput createDtoToUseCaseInput(CreateDiscountDto createDiscountDto) {
        if(createDiscountDto == null) {
            return null;
        }

        return new CreateDiscountUseCaseInput(createDiscountDto.getValue(), createDiscountDto.getProductId());
    }

    public ReadDiscountDto domainToReadDto(Discount discount) {
        if(discount == null) {
            return null;
        }

        return new ReadDiscountDto(discount.getId(), discount.getValue());
    }
}
