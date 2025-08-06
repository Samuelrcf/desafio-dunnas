package com.dunnas.desafio.components.client.infra.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dunnas.desafio.components.client.infra.persistence.entities.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long>{

}
