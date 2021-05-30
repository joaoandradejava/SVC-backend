package com.totalmed.svcbackend.domain.model.enumeration;

public enum Perfil {
	USUARIO("ROLE_USUARIO"), ADMIN("ROLE_ADMIN");

	private String descricao;

	private Perfil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
