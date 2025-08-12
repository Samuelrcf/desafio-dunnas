package com.dunnas.desafio.components.product.application.usecases;

import com.dunnas.desafio.components.product.application.usecases.outputs.CreateProductUseCaseOutput;
import java.util.List;

public interface ListProductsBySupplierUseCase {
    List<CreateProductUseCaseOutput> execute();
}
