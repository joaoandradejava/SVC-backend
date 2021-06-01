package com.totalmed.svcbackend.api.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CarteiraVacinacaoCreateInput {

	@Size(max = 255)
	@NotBlank
	private String nome;

	public CarteiraVacinacaoCreateInput() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
