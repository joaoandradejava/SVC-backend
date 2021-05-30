package com.totalmed.svcbackend.domain.observer;

import com.totalmed.svcbackend.domain.model.Usuario;

public class UsuarioEsqueceuSenhaObserver {
	private final Usuario usuario;
	private final String novaSenha;

	public UsuarioEsqueceuSenhaObserver(Usuario usuario, String novaSenha) {
		this.usuario = usuario;
		this.novaSenha = novaSenha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

}
