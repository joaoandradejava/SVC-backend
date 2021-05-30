package com.totalmed.svcbackend.domain.service.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.totalmed.svcbackend.domain.exception.NegocioException;
import com.totalmed.svcbackend.domain.model.Usuario;
import com.totalmed.svcbackend.domain.repository.UsuarioRepository;

@Component
public class EmailUniqueValidator {

	@Autowired
	private UsuarioRepository repository;

	public void verificarSeEmailEValido(Usuario usuario) {
		Optional<Usuario> obj = repository.findByEmail(usuario.getEmail());

		if (obj.isPresent() && !obj.get().equals(usuario)) {
			throw new NegocioException(
					String.format("Já existe um Usuario com o email '%s' cadastrado no sistema!", usuario.getEmail()));
		}
	}
}
