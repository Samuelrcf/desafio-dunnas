package com.dunnas.desafio.components.client.infra.persistence.mappers;

import com.dunnas.desafio.components.client.domain.models.Client;
import com.dunnas.desafio.components.client.infra.persistence.entities.ClientEntity;
import com.dunnas.desafio.components.user.infra.persistence.mappers.UserEntityMapper;

public class ClientEntityMapper {

    public static ClientEntity modelToEntity(Client client) {
        if (client == null) return null;

        ClientEntity entity = new ClientEntity();
        entity.setId(client.getId());
        entity.setName(client.getName());
        entity.setCpf(client.getCpf());
        entity.setBirthDate(client.getBirthDate());
        entity.setBalance(client.getBalance());

        entity.setUserEntity(UserEntityMapper.modelToEntity(client.getUser()));

        return entity;
    }

    public static Client entityToModel(ClientEntity entity) {
        if (entity == null) return null;

        return new Client(
            entity.getId(),
            entity.getName(),
            entity.getCpf(),
            entity.getBirthDate(),
            entity.getBalance(),
            UserEntityMapper.entityToModel(entity.getUserEntity())
        );
    }
}

