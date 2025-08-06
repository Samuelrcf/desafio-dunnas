package com.dunnas.desafio.components.user.infra.persistence.mappers;

import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.user.domain.models.User;
import com.dunnas.desafio.components.user.infra.persistence.entities.UserEntity;

@Component
public class UserEntityMapper {
	
    public UserEntity modelToEntity(User user) {
        if (user == null) return null;

        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUserName(user.getUserName());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole());

        return entity;
    }

    public User entityToModel(UserEntity entity) {
        if (entity == null) return null;

        return new User(
            entity.getId(),
            entity.getUserName(),
            entity.getPassword(),
            entity.getRole()
        );
    }
}
