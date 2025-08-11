package com.dunnas.desafio.components.product.infra.persistence.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dunnas.desafio.components.product.infra.persistence.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

	@Query(value = "SELECT * FROM find_product_by_name(:name)", nativeQuery = true)
	Optional<ProductEntity> findByName(@Param("name") String name);

	@Query(value = "SELECT * FROM find_products_by_supplier_id(:supplierId)", nativeQuery = true)
	List<ProductEntity> findBySupplierEntityIdAndDeletedFalse(@Param("supplierId") Long supplierId);

	@Query(value = "SELECT * FROM find_all_active_products()", nativeQuery = true)
	List<ProductEntity> findByDeletedFalse();

}
