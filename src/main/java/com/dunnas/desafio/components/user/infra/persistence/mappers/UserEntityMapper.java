package com.dunnas.desafio.components.user.infra.persistence.mappers;

import com.dunnas.desafio.components.user.domain.models.User;
import com.dunnas.desafio.components.user.infra.persistence.entities.UserEntity;

public class UserEntityMapper {
	
    public static UserEntity modelToEntity(User user) {
        if (user == null) return null;

        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUserName(user.getUserName());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole());

        return entity;
    }

    public static User entityToModel(UserEntity entity) {
        if (entity == null) return null;

        return new User(
            entity.getId(),
            entity.getUserName(),
            entity.getPassword(),
            entity.getRole()
        );
    }
}
