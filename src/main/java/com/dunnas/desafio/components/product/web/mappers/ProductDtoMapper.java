package com.dunnas.desafio.components.product.web.mappers;

import com.dunnas.desafio.components.product.domain.models.Coupon;
import com.dunnas.desafio.components.product.domain.models.Discount;
import com.dunnas.desafio.components.product.web.dtos.ReadCouponDto;
import com.dunnas.desafio.components.product.web.dtos.ReadDiscountDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.product.application.usecases.inputs.CreateProductUseCaseInput;
import com.dunnas.desafio.components.product.application.usecases.outputs.CreateProductUseCaseOutput;
import com.dunnas.desafio.components.product.web.dtos.CreateProductDto;
import com.dunnas.desafio.components.product.web.dtos.ReadProductDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductDtoMapper {
	private final DiscountDtoMapper discountDtoMapper;
	private final CouponDtoMapper couponDtoMapper;


	public CreateProductUseCaseInput createDtoToCreateUseCaseInput(CreateProductDto requestDto) {

		return new CreateProductUseCaseInput(requestDto.getName(), requestDto.getDescription(), requestDto.getPrice());
	}

	public ReadProductDto createUseCaseOutputToReadDto(CreateProductUseCaseOutput output) {

		List<ReadDiscountDto> discountsDtos = new ArrayList<>();
		for(Discount discount : output.discounts()) {
			discountsDtos.add(discountDtoMapper.domainToReadDto(discount));
		}

		List<ReadCouponDto> couponsDtos = new ArrayList<>();
		for(Coupon coupon : output.coupons()) {
			couponsDtos.add(couponDtoMapper.domainToReadDto(coupon));
		}


		return new ReadProductDto(output.id(), output.name(), output.description(), output.price(), discountsDtos, couponsDtos);
	}

	public List<ReadProductDto> listUseCaseOutputToReadDto(List<CreateProductUseCaseOutput> output) {
		List<ReadProductDto> readProductDtos = new ArrayList<>();
		for(CreateProductUseCaseOutput outputDto : output) {
			readProductDtos.add(this.createUseCaseOutputToReadDto(outputDto));
		}

		return readProductDtos;
	}
}
