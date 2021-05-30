package com.totalmed.svcbackend.domain.model;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {
	private String cep;
	private String endereco;
	private String complemento;
	private String bairro;
	private String numero;
	private String cidade;
	private String estado;

	public Endereco() {
	}

	public Endereco(String cep, String endereco, String complemento, String bairro, String numero, String cidade,
			String estado) {
		this.cep = cep;
		this.endereco = endereco;
		this.complemento = complemento;
		this.bairro = bairro;
		this.numero = numero;
		this.cidade = cidade;
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
