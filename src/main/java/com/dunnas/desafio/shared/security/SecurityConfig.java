package com.dunnas.desafio.shared.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.dunnas.desafio.shared.exceptions.handler.CustomAccessDeniedHandler;
import com.dunnas.desafio.shared.exceptions.handler.CustomAuthenticationEntryPoint;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
	
    SecurityFilter securityFilter;
    CustomAccessDeniedHandler accessDeniedHandler;
    CustomAuthenticationEntryPoint authenticationEntryPoint;
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) 
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors((cors) -> cors.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOriginPatterns(Collections.singletonList("*"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                })).authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                .build();
                /*.exceptionHandling(e -> e.accessDeniedHandler(accessDeniedHandler)
                        .authenticationEntryPoint(authenticationEntryPoint))
                .authorizeHttpRequests(authorize -> authorize
                		
                		.requestMatchers(HttpMethod.GET, "/register", "/login").permitAll()

                        .requestMatchers(HttpMethod.GET, "/clients/**").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.POST, "/clients/**").hasRole("CLIENT")
                        
                        .requestMatchers(HttpMethod.GET, "/suppliers/**").hasRole("SUPPLIER")
			    		.requestMatchers(HttpMethod.POST, "/supplier/**").hasRole("SUPPLIER")
			    		
			    		.requestMatchers(HttpMethod.POST, "/products/**").hasRole("SUPPLIER")
			    		.requestMatchers(HttpMethod.GET, "/products/supplier", "/products/new").hasRole("SUPPLIER")
			    		
			    		.requestMatchers(HttpMethod.POST, "/orders/**").hasRole("CLIENT")
			    		
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();*/
    }
	
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
