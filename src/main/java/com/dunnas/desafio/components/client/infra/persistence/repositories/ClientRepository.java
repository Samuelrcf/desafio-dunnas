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

	@Query(value = "SELECT * FROM find_client_by_cpf(:cpf)", nativeQuery = true)
	Optional<ClientEntity> findByCpf(@Param("cpf") String cpf);

	@Query(value = "SELECT * FROM find_client_by_user_id(:userId)", nativeQuery = true)
	Optional<ClientEntity> findByUserEntityId(@Param("userId") Long userId);

	@Query(value = "SELECT exists_client_by_username(:username)", nativeQuery = true)
	Boolean existsByUserEntityUserName(@Param("username") String username);
	
	@Transactional
	@Modifying
	@Query(value = "CALL update_client_balance(:clientId, :balance)", nativeQuery = true)
	void updateBalance(@Param("clientId") Long clientId, @Param("balance") BigDecimal balance);
	
}
