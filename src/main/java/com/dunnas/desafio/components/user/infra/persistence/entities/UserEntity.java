package com.dunnas.desafio.components.user.infra.persistence.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_users")
public class UserEntity implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String userName;
	private String password;
	private String role;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.role.equals("CLIENT")) {
			return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
		} else if (this.role.equals("SUPPLIER")) {
			return List.of(new SimpleGrantedAuthority("ROLE_SUPPLIER"));
		}
		return null;
	}
	@Override
	public String getUsername() {
		return userName;
	}
}
