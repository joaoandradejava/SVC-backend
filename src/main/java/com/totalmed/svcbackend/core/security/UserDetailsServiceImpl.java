package com.totalmed.svcbackend.core.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.totalmed.svcbackend.domain.model.Usuario;
import com.totalmed.svcbackend.domain.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> obj = repository.findByCpf(username);

		if (obj.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}

		Usuario usuario = obj.get();
		return new UsuarioAutenticado(usuario.getId(), usuario.getCpf(), usuario.getSenha(), usuario.getPerfis(),
				usuario.isAdmin());
	}

}
