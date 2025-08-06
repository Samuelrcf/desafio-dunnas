package com.dunnas.desafio.components.client.application.gateways;

import java.util.Optional;

import com.dunnas.desafio.components.client.domain.models.Client;

public interface ClientRepositoryGateway {

    Client create(Client Client);

    Optional<Client> findById(Long id);

    Optional<Client> findByCpf(String cpf);
    
    boolean existsById(Long id);

}
