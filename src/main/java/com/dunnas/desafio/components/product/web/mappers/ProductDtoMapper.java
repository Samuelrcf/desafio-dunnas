package com.dunnas.desafio.components.product.web.mappers;

import com.dunnas.desafio.components.product.application.usecases.inputs.CreateProductUseCaseInput;
import com.dunnas.desafio.components.product.application.usecases.outputs.CreateProductUseCaseOutput;
import com.dunnas.desafio.components.product.web.dtos.CreateProductDto;
import com.dunnas.desafio.components.product.web.dtos.ReadProductDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductDtoMapper {

    private final DiscountDtoMapper discountDtoMapper;
    private final CouponDtoMapper couponDtoMapper;


    public CreateProductUseCaseInput createDtoToCreateUseCaseInput(CreateProductDto requestDto) {

        return new CreateProductUseCaseInput(requestDto.getName(), requestDto.getDescription(),
                requestDto.getPrice());
    }

    public ReadProductDto createUseCaseOutputToReadDto(CreateProductUseCaseOutput output) {
        return new ReadProductDto(output.id(), output.name(), output.description(), output.price(),
                discountDtoMapper.domainToReadDto(output.discount()),
                couponDtoMapper.domainToReadDto(output.coupon()));
    }

    public List<ReadProductDto> listUseCaseOutputToReadDto(
            List<CreateProductUseCaseOutput> output) {
        List<ReadProductDto> readProductDtos = new ArrayList<>();
        for (CreateProductUseCaseOutput outputDto : output) {
            readProductDtos.add(this.createUseCaseOutputToReadDto(outputDto));
        }

        return readProductDtos;
    }
}
