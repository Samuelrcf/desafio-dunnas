package com.dunnas.desafio.components.client.application.gateways;

import java.util.Optional;

import com.dunnas.desafio.components.client.domain.models.Client;

public interface ClientRepositoryGateway {

    Client create(Client Client);

    Client update(Client Client);

    Optional<Client> findById(Long id);

    Optional<Client> findByName(String name);

    boolean existsById(Long id);

    void deleteById(Long id);
}
