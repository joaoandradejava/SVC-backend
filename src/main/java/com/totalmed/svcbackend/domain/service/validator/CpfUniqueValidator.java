package com.totalmed.svcbackend.domain.service.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.totalmed.svcbackend.domain.exception.NegocioException;
import com.totalmed.svcbackend.domain.model.Usuario;
import com.totalmed.svcbackend.domain.repository.UsuarioRepository;

@Component
public class CpfUniqueValidator {

	@Autowired
	private UsuarioRepository repository;

	public void verificarSeCpfEValido(Usuario usuario) {
		Optional<Usuario> obj = repository.findByCpf(usuario.getCpf());

		if (obj.isPresent() && !obj.get().equals(usuario)) {
			throw new NegocioException(
					String.format("Já existe um Usuario com o cpf '%s' cadastrado no sistema!", usuario.getCpf()));
		}
	}
}
