package com.dunnas.desafio.components.user.infra.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dunnas.desafio.components.user.infra.persistence.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

}
