package com.dunnas.desafio.components.client.infra.persistence.repositories;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dunnas.desafio.components.client.infra.persistence.entities.ClientEntity;

import jakarta.transaction.Transactional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long>{

	Optional<ClientEntity> findByCpf(String cpf);
	
	Optional<ClientEntity> findByUserEntityId(Long id);
	
	Boolean existsByUserEntityUserName(String name);
	
	@Transactional
	@Modifying
	@Query("UPDATE ClientEntity c SET c.balance = :balance WHERE c.id = :clientId")
	void updateBalance(@Param("clientId") Long clientId, @Param("balance") BigDecimal balance);

	
}
