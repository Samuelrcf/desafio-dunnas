package com.dunnas.desafio.components.supplier.infra.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dunnas.desafio.components.supplier.infra.persistence.entities.SupplierEntity;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Long>{

	@Query(value = "SELECT * FROM find_supplier_by_cnpj(:cnpj)", nativeQuery = true)
	Optional<SupplierEntity> findByCnpj(@Param("cnpj") String cnpj);

	@Query(value = "SELECT * FROM find_supplier_by_user_id(:userId)", nativeQuery = true)
	Optional<SupplierEntity> findByUserEntityId(@Param("userId") Long id);

	@Query(value = "SELECT exists_supplier_by_username(:userName)", nativeQuery = true)
	Boolean existsByUserEntityUserName(@Param("userName") String name);

	@Query(value = "SELECT exists_supplier_by_product_id(:productId)", nativeQuery = true)
	Boolean existsByProductEntitiesId(@Param("productId") Long id);

}
