package com.dunnas.desafio.components.supplier.infra.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dunnas.desafio.components.supplier.infra.persistence.entities.SupplierEntity;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Long>{

	Optional<SupplierEntity> findByCnpj(String cnpj);
	
	Optional<SupplierEntity> findByUserEntityId(Long id);
}
