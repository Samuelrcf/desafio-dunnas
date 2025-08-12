package com.dunnas.desafio.components.product.web.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dunnas.desafio.components.product.application.usecases.CreateCouponUseCase;
import com.dunnas.desafio.components.product.application.usecases.CreateDiscountUseCase;
import com.dunnas.desafio.components.product.application.usecases.CreateProductUseCase;
import com.dunnas.desafio.components.product.application.usecases.DeleteCouponUseCase;
import com.dunnas.desafio.components.product.application.usecases.DeleteDiscountUseCase;
import com.dunnas.desafio.components.product.application.usecases.DeleteProductUseCase;
import com.dunnas.desafio.components.product.application.usecases.ListProductsBySupplierUseCase;
import com.dunnas.desafio.components.product.application.usecases.ListProductsUseCase;
import com.dunnas.desafio.components.product.application.usecases.inputs.CreateProductUseCaseInput;
import com.dunnas.desafio.components.product.application.usecases.outputs.CreateProductUseCaseOutput;
import com.dunnas.desafio.components.product.web.dtos.CreateCouponDto;
import com.dunnas.desafio.components.product.web.dtos.CreateDiscountDto;
import com.dunnas.desafio.components.product.web.dtos.CreateProductDto;
import com.dunnas.desafio.components.product.web.dtos.ReadProductDto;
import com.dunnas.desafio.components.product.web.mappers.CouponDtoMapper;
import com.dunnas.desafio.components.product.web.mappers.DiscountDtoMapper;
import com.dunnas.desafio.components.product.web.mappers.ProductDtoMapper;

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
    private final DeleteDiscountUseCase deleteDiscountUseCase;
    private final DeleteCouponUseCase deleteCouponUseCase;


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

    @PostMapping("/coupon")
    public String createCouponFromForm(@RequestParam Long productId,
            @RequestParam String name,
            @RequestParam String code,
            @RequestParam @DecimalMin("0.0") @DecimalMax("1.0") BigDecimal value,
            RedirectAttributes redirectAttributes) {
        try {
            CreateDiscountDto discountDto = new CreateDiscountDto(value, productId);
            CreateCouponDto couponDto = new CreateCouponDto(name, code, discountDto);

            var input = couponDtoMapper.createDtoToUseCaseInput(couponDto);
            createCouponUseCase.execute(input);

            redirectAttributes.addFlashAttribute("successMessage", "Cupom aplicado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao aplicar cupom: " + e.getMessage());
        }
        return "redirect:/products/supplier";
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
        //System.out.println("Product" + readProductDtos.get(0).getDiscount().getValue());
        model.addAttribute("products", readProductDtos);
        return "supplierProducts";
    }
    
    @PostMapping("/{id}")
    public String delete(@PathVariable Long id) throws Exception {
        deleteProductUseCase.execute(id);
        return "redirect:/products/supplier";
    }

    @PostMapping("/discount/delete")
    public String removeDiscount(@RequestParam Long discountId) {
        deleteDiscountUseCase.execute(discountId);
        return "redirect:/products/supplier";
    }

    @PostMapping("/coupon/delete")
    public String removeCoupon(@RequestParam Long couponId, RedirectAttributes redirectAttributes) {
        try {
            deleteCouponUseCase.execute(couponId);
            redirectAttributes.addFlashAttribute("successMessage", "Cupom removido com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao remover cupom: " + e.getMessage());
        }
        return "redirect:/products/supplier";
    }

}
