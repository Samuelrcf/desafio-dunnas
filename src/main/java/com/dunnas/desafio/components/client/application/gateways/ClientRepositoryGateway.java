package com.dunnas.desafio.components.client.application.gateways;

import java.util.Optional;

import com.dunnas.desafio.components.client.domain.models.Client;
import com.dunnas.desafio.components.order.domain.models.Order;
import com.dunnas.desafio.shared.response.PaginationResult;

public interface ClientRepositoryGateway {

    Client create(Client client);

    Optional<Client> findById(Long id);
    
    Optional<Client> findByUserEntityId(Long id);
    
    Optional<Client> findByCpf(String cpf);
    
    boolean existsById(Long id);

    PaginationResult<Order> getHistory(Long clientId, int page, int size);

	Boolean existsByUserEntityUserName(String name);

	void updateBalance(Client client);
}
