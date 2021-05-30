package com.totalmed.svcbackend.domain.observer;

import com.totalmed.svcbackend.domain.model.Usuario;

public class UsuarioCadastradoObserver {
	private final Usuario usuarioCadastrado;

	public UsuarioCadastradoObserver(Usuario usuarioCadastrado) {
		this.usuarioCadastrado = usuarioCadastrado;
	}

	public Usuario getUsuarioCadastrado() {
		return usuarioCadastrado;
	}
}
