package com.totalmed.svcbackend.core.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.totalmed.svcbackend.domain.model.enumeration.Perfil;

public class UsuarioAutenticado implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String cpf;
	private String senha;
	private Collection<? extends GrantedAuthority> perfis;
	private boolean isAdmin;

	public UsuarioAutenticado() {
	}

	public UsuarioAutenticado(Long id, String cpf, String senha, Set<Perfil> perfis, boolean isAdmin) {
		this.id = id;
		this.cpf = cpf;
		this.senha = senha;
		this.perfis = perfis.stream().map(perfil -> new SimpleGrantedAuthority(perfil.getDescricao()))
				.collect(Collectors.toList());
		this.isAdmin = isAdmin;
	}

	public Long getId() {
		return id;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return perfis;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return cpf;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
