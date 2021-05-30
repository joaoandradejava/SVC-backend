package com.totalmed.svcbackend.domain.service;

import org.springframework.stereotype.Service;

import com.totalmed.svcbackend.core.security.UsuarioAutenticado;

@Service
public class PermissaoService {

	public boolean isNaoTemPermissao(UsuarioAutenticado usuarioAutenticado, Long usuarioId) {
		return usuarioAutenticado.getId() != usuarioId && !usuarioAutenticado.isAdmin();
	}
}
