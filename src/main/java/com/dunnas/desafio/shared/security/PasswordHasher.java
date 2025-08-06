package com.dunnas.desafio.shared.security;

public interface PasswordHasher {
    String hash(String plainPassword);
}

