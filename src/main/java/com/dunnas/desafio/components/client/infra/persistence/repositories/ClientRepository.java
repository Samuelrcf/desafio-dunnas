package com.dunnas.desafio.components.client.infra.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dunnas.desafio.components.client.infra.persistence.entities.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long>{

	Optional<ClientEntity> findByCpf(String cpf);
	
}
