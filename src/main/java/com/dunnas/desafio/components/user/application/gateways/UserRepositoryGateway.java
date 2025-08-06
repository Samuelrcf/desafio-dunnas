package com.dunnas.desafio.components.user.application.gateways;

import java.util.Optional;

import com.dunnas.desafio.components.user.domain.models.User;

public interface UserRepositoryGateway {

    User create(User User);

    User update(User User);

    Optional<User> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}
