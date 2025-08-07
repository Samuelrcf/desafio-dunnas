package com.dunnas.desafio.shared.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.user.domain.models.User;

@Component
public class ApplicationAuditAware implements AuditorAware<Long>, CurrentUserProvider {
    
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        User userPrincipal = (User) auth.getPrincipal();
        return Optional.ofNullable(userPrincipal.getId());
    }

    @Override
    public Optional<Long> getCurrentUserId() {
        return getCurrentAuditor();
    }
}

