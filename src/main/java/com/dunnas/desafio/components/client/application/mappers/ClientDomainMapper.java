package com.dunnas.desafio.components.client.application.mappers;

import java.util.List;

import com.dunnas.desafio.components.client.application.usecases.outputs.AddCreditUseCaseOutput;
import com.dunnas.desafio.components.client.application.usecases.outputs.CheckHistoryUseCaseOutput;
import com.dunnas.desafio.components.client.application.usecases.outputs.CreateClientUseCaseOutput;
import com.dunnas.desafio.components.client.application.usecases.outputs.ProductOutput;
import com.dunnas.desafio.components.client.domain.models.Client;
import com.dunnas.desafio.components.order.domain.models.Order;
import com.dunnas.desafio.components.user.application.mappers.UserDomainMapper;
import com.dunnas.desafio.components.user.application.usecases.outputs.CreateUserOutput;

public class ClientDomainMapper {
	private final UserDomainMapper userDomainMapper;

	public ClientDomainMapper(UserDomainMapper userDomainMapper) {
		this.userDomainMapper = userDomainMapper;
	}

	public CreateClientUseCaseOutput domainToCreateClientUseCaseOutput(Client domain) {
		CreateUserOutput userOutput = userDomainMapper.domainToOutput(domain.getUser());

		return new CreateClientUseCaseOutput(domain.getId(), domain.getName(), domain.getCpf(), domain.getBirthDate(),
				domain.getBalance(), userOutput);
	}

	public AddCreditUseCaseOutput domainToAddCreditUseCaseOutput(Client domain) {
		CreateUserOutput userOutput = userDomainMapper.domainToOutput(domain.getUser());

		return new AddCreditUseCaseOutput(domain.getId(), domain.getName(), domain.getCpf(), domain.getBirthDate(),
				domain.getBalance(), userOutput);
	}

	public CheckHistoryUseCaseOutput domainToCheckHistoryOutput(Order order) {
		List<ProductOutput> products = order.getProducts().stream()
				.map(p -> new ProductOutput(p.getId(), p.getName(), p.getDescription(), p.getPrice())).toList();

		return new CheckHistoryUseCaseOutput(order.getOrderCode(), order.getSupplier().getName(), products,
				order.getTotal(), order.getCreationDate());
	}
}
