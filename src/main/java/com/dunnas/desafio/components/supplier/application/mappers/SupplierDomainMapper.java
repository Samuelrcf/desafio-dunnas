package com.dunnas.desafio.components.supplier.application.mappers;

import java.util.List;

import com.dunnas.desafio.components.order.domain.models.Order;
import com.dunnas.desafio.components.supplier.application.usecases.outputs.CheckHistoryUseCaseOutput;
import com.dunnas.desafio.components.supplier.application.usecases.outputs.CreateSupplierUseCaseOutput;
import com.dunnas.desafio.components.supplier.application.usecases.outputs.FetchSupplierInfoUseCaseOutput;
import com.dunnas.desafio.components.supplier.application.usecases.outputs.ProductOutput;
import com.dunnas.desafio.components.supplier.domain.models.Supplier;
import com.dunnas.desafio.components.user.application.mappers.UserDomainMapper;
import com.dunnas.desafio.components.user.application.usecases.outputs.CreateUserOutput;
import com.dunnas.desafio.components.user.application.usecases.outputs.FetchUserOutput;

public class SupplierDomainMapper {
    private final UserDomainMapper userDomainMapper;

    public SupplierDomainMapper(UserDomainMapper userDomainMapper) {
        this.userDomainMapper = userDomainMapper;
    }

    public CreateSupplierUseCaseOutput domainToOutput(Supplier domain) {
        CreateUserOutput userOutput = userDomainMapper.domainToCreateUserOutput(domain.getUser());

        return new CreateSupplierUseCaseOutput(domain.getId(), domain.getName(), domain.getCnpj(), userOutput);
    }
    
	public CheckHistoryUseCaseOutput domainToCheckHistoryOutput(Order order) {
		List<ProductOutput> products = order.getProducts().stream()
				.map(p -> new ProductOutput(p.getId(), p.getName(), p.getDescription(), p.getPrice())).toList();

		return new CheckHistoryUseCaseOutput(order.getOrderCode(), order.getSupplier().getName(), products,
				order.getTotal(), order.getCreationDate());
	}
	
	public FetchSupplierInfoUseCaseOutput domainToFetchSupplierInfoUseCaseOutput(Supplier domain) {
		
		FetchUserOutput fetchUserOutput = userDomainMapper.domainToFetchUserOutput(domain.getUser());
		
		return new FetchSupplierInfoUseCaseOutput(domain.getId(), domain.getName(), domain.getCnpj(), fetchUserOutput);
	}
}
