package com.dunnas.desafio.components.user.infra.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.dunnas.desafio.components.user.application.exceptions.UsernameOrPasswordInvalidException;
import com.dunnas.desafio.components.user.application.gateways.AuthenticationService;
import com.dunnas.desafio.components.user.domain.models.User;
import com.dunnas.desafio.components.user.infra.persistence.entities.UserEntity;
import com.dunnas.desafio.components.user.infra.persistence.mappers.UserEntityMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserEntityMapper userEntityMapper;

    @Override
    public User authenticate(String username, String password) throws Exception {
        try {
            var token = new UsernamePasswordAuthenticationToken(username, password);
            var auth = authenticationManager.authenticate(token);
            var user = (UserEntity) auth.getPrincipal();
            return userEntityMapper.entityToModel(user);
        } catch (BadCredentialsException ex) {
            throw new UsernameOrPasswordInvalidException(ex);
        } catch (InternalAuthenticationServiceException ex) {
            throw new InternalAuthenticationServiceException("Erro interno de autenticação", ex);
        }
    }

}


