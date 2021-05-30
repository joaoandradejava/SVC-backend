package com.totalmed.svcbackend.api.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

public class UsuarioCreateInput {

	@Size(min = 3, max = 255)
	@NotBlank
	private String nome;

	@Size(min = 11, max = 11)
	@CPF
	@NotBlank
	private String cpf;

	@Email
	@Size(max = 255)
	@NotBlank
	private String email;

	@Size(max = 255)
	@NotBlank
	private String senha;

	@Size(max = 11)
	@NotBlank
	private String telefone;

	public UsuarioCreateInput() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
