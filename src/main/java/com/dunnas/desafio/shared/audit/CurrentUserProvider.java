package com.dunnas.desafio.shared.audit;

import java.util.Optional;

public interface CurrentUserProvider {
    Optional<Long> getCurrentUserId();
}
