package com.dunnas.desafio.components.product.web.mappers;

import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.product.application.usecases.inputs.CreateCouponUseCaseInput;
import com.dunnas.desafio.components.product.application.usecases.inputs.CreateDiscountUseCaseInput;
import com.dunnas.desafio.components.product.domain.models.Coupon;
import com.dunnas.desafio.components.product.web.dtos.CreateCouponDto;
import com.dunnas.desafio.components.product.web.dtos.ReadCouponDto;
import com.dunnas.desafio.components.product.web.dtos.ReadDiscountDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CouponDtoMapper {

    private final DiscountDtoMapper discountDtoMapper;

    public CreateCouponUseCaseInput createDtoToUseCaseInput(
            CreateCouponDto createCouponDto) {
        CreateDiscountUseCaseInput createDiscountUseCaseInput = discountDtoMapper.createDtoToUseCaseInput(
                createCouponDto.getDiscount());

        return new CreateCouponUseCaseInput(createCouponDto.getName(),
                createCouponDto.getCode(), createDiscountUseCaseInput);
    }

    public ReadCouponDto domainToReadDto(Coupon coupon) {
        ReadDiscountDto discountDto = discountDtoMapper.domainToReadDto(coupon.getDiscount());
        return new ReadCouponDto(coupon.getId(), coupon.getName(), coupon.getCode(), discountDto);
    }
}
