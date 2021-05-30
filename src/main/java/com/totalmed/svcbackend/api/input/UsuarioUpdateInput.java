package com.totalmed.svcbackend.api.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioUpdateInput {

	@Size(min = 3, max = 255)
	@NotBlank
	private String nome;

	@Email
	@Size(max = 255)
	@NotBlank
	private String email;

	@Size(max = 11)
	@NotBlank
	private String telefone;

	public UsuarioUpdateInput() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
