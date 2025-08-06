package com.dunnas.desafio.components.user.infra.persistence.gateways;

import java.util.Optional;

import com.dunnas.desafio.components.user.domain.models.User;
import com.dunnas.desafio.components.user.infra.persistence.entities.UserEntity;
import com.dunnas.desafio.components.user.infra.persistence.mappers.UserEntityMapper;
import com.dunnas.desafio.components.user.infra.persistence.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

import com.dunnas.desafio.components.user.application.gateways.UserRepositoryGateway;

@RequiredArgsConstructor
public class UserRepositoryGatewayImpl implements UserRepositoryGateway{

    private final UserRepository repository;
    private final UserEntityMapper mapper;
	
	@Override
	public User create(User user) {
		UserEntity entity = mapper.modelToEntity(user); 
		repository.save(entity);
		return mapper.entityToModel(entity);
	}

	@Override
	public User update(User user) {
		UserEntity entity = mapper.modelToEntity(user); 
		repository.save(entity);
		return mapper.entityToModel(entity);
	}

	@Override
	public Optional<User> findById(Long id) {
        Optional<UserEntity> optionalEntity = repository.findById(id);

        if (optionalEntity.isPresent()) {
            UserEntity entity = optionalEntity.get();
            User model = mapper.entityToModel(entity);

            return Optional.of(model);
        }
        
        return Optional.empty();
	}

	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
