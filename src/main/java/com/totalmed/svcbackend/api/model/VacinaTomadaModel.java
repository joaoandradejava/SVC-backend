package com.totalmed.svcbackend.api.model;

public class VacinaTomadaModel {
	private Long id;
	private String nome;
	private String data;

	public VacinaTomadaModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
