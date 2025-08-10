package com.dunnas.desafio.components.product.web.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dunnas.desafio.components.product.application.usecases.CreateCouponUseCase;
import com.dunnas.desafio.components.product.application.usecases.CreateDiscountUseCase;
import com.dunnas.desafio.components.product.application.usecases.CreateProductUseCase;
import com.dunnas.desafio.components.product.application.usecases.DeleteProductUseCase;
import com.dunnas.desafio.components.product.application.usecases.ListProductsBySupplierUseCase;
import com.dunnas.desafio.components.product.application.usecases.ListProductsUseCase;
import com.dunnas.desafio.components.product.application.usecases.inputs.CreateCouponUseCaseInput;
import com.dunnas.desafio.components.product.application.usecases.inputs.CreateProductUseCaseInput;
import com.dunnas.desafio.components.product.application.usecases.outputs.CreateProductUseCaseOutput;
import com.dunnas.desafio.components.product.web.dtos.CreateCouponDto;
import com.dunnas.desafio.components.product.web.dtos.CreateDiscountDto;
import com.dunnas.desafio.components.product.web.dtos.CreateProductDto;
import com.dunnas.desafio.components.product.web.dtos.ReadProductDto;
import com.dunnas.desafio.components.product.web.mappers.CouponDtoMapper;
import com.dunnas.desafio.components.product.web.mappers.DiscountDtoMapper;
import com.dunnas.desafio.components.product.web.mappers.ProductDtoMapper;
import com.dunnas.desafio.shared.response.ApiSuccessResponse;
import com.dunnas.desafio.shared.response.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/products")
public class ProductController {
	
    private final ProductDtoMapper mapper;
    private final CreateProductUseCase createUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final CreateDiscountUseCase createDiscountUseCase;
    private final DiscountDtoMapper discountDtoMapper;
    private final CreateCouponUseCase createCouponUseCase;
    private final CouponDtoMapper couponDtoMapper;
    private final ListProductsUseCase listProductsUseCase;
    private final ListProductsBySupplierUseCase listProductsBySupplierUseCase;

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new CreateProductDto());
        return "createProduct"; 
    }

    @PostMapping
    public String createFromForm(@ModelAttribute("product") @Valid CreateProductDto createProductDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) throws Exception {
        if (bindingResult.hasErrors()) {
            return "createProduct";
        }

        CreateProductUseCaseInput input = mapper.createDtoToCreateUseCaseInput(createProductDto);
        createUseCase.execute(input);

        redirectAttributes.addFlashAttribute("successMessage", "Produto criado com sucesso!");
        return "redirect:/products/supplier"; 
    }
    
    @PostMapping("/discount")
    public String applyDiscount(@RequestParam Long productId,
                                @RequestParam @DecimalMin("0.0") @DecimalMax("1.0") BigDecimal value,
                                RedirectAttributes redirectAttributes) {
        try {
            CreateDiscountDto dto = new CreateDiscountDto(value, productId);
            var input = discountDtoMapper.createDtoToUseCaseInput(dto);
            createDiscountUseCase.execute(input);

            redirectAttributes.addFlashAttribute("successMessage", "Desconto aplicado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao aplicar desconto: " + e.getMessage());
        }
        return "redirect:/products/supplier";
    }

    @PostMapping("/coupons")
    public ResponseEntity<ApiSuccessResponse<ReadProductDto>> createCoupon(@Valid @RequestBody CreateCouponDto createCouponDto, HttpServletRequest request) throws Exception {

        CreateCouponUseCaseInput input = couponDtoMapper.createDtoToUseCaseInput(createCouponDto);
        CreateProductUseCaseOutput output = createCouponUseCase.execute(input);
        ReadProductDto readDto = mapper.createUseCaseOutputToReadDto(output);

        ApiSuccessResponse<ReadProductDto> response = ResponseUtil.success(readDto, "Cupom criado com sucesso.", request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public String getAll(Model model) {
        List<CreateProductUseCaseOutput> products = listProductsUseCase.execute();
        List<ReadProductDto> readProductDtos = mapper.listUseCaseOutputToReadDto(products);
        model.addAttribute("products", readProductDtos);
        return "productList";  
    }
    
    @GetMapping("/supplier")
    public String getAllBySupplier(Model model) {
        List<CreateProductUseCaseOutput> products = listProductsBySupplierUseCase.execute();
        List<ReadProductDto> readProductDtos = mapper.listUseCaseOutputToReadDto(products);

        model.addAttribute("products", readProductDtos);
        return "supplierProducts"; 
    }
    
    @PostMapping("/{id}")
    public String delete(@PathVariable Long id) throws Exception {
        deleteProductUseCase.execute(id);
        return "redirect:/products/supplier";
    }

}
