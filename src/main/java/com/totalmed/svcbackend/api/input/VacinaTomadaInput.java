package com.totalmed.svcbackend.api.input;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class VacinaTomadaInput {

	@Size(max = 255)
	@NotBlank
	private String nome;

	@NotNull
	private LocalDate data;

	public VacinaTomadaInput() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

}
