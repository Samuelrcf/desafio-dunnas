package com.dunnas.desafio.components.product.application.mappers;

import com.dunnas.desafio.components.product.application.usecases.outputs.CreateProductUseCaseOutput;
import com.dunnas.desafio.components.product.domain.models.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductDomainMapper {

    public CreateProductUseCaseOutput domainToOutput(Product domain) {

        return new CreateProductUseCaseOutput(domain.getId(), domain.getName(),
                domain.getDescription(), domain.getPrice(), domain.getDiscounts(),
                domain.getCoupons());
    }

    public List<CreateProductUseCaseOutput> listDomainToOutput(List<Product> models) {
        List<CreateProductUseCaseOutput> products = new ArrayList<>();
        for(Product domain : models) {
            products.add(this.domainToOutput(domain));
        }

        return products;
    }
}
