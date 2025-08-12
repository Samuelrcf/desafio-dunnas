package com.dunnas.desafio.shared.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dunnas.desafio.components.user.application.gateways.TokenProvider;
import com.dunnas.desafio.components.user.infra.persistence.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	TokenProvider tokenProvider;
    UserRepository userRepository;
    
    public SecurityFilter(TokenProvider tokenProvider, UserRepository userRepository) {
		this.tokenProvider = tokenProvider;
		this.userRepository = userRepository;
	}
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = this.recoverToken(request);
            if (token != null) {
                String userName = tokenProvider.validateToken(token);
                UserDetails user = userRepository.findByUserName(userName);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            logger.warn("Falha na autenticação do token: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

	private String recoverToken(HttpServletRequest request) {
	    if (request.getCookies() != null) {
	        for (Cookie cookie : request.getCookies()) {
	            if ("Authorization".equals(cookie.getName())) {
	                return cookie.getValue();
	            }
	        }
	    }
	    String authHeader = request.getHeader("Authorization");
	    if (authHeader != null && authHeader.startsWith("Bearer ")) {
	        return authHeader.replace("Bearer ", "");
	    }
	    return null;
	}

}
