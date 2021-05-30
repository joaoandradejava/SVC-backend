package com.totalmed.svcbackend.api.model;

import java.util.ArrayList;
import java.util.List;

public class UsuarioFullModel {
	private Long id;
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	private Boolean isAtivo;
	private Boolean isAdmin;
	private EnderecoModel endereco;
	private List<FeedbackModel> feedbacks = new ArrayList<>();
	private List<AgendamentoVacinaModel> agendamentos = new ArrayList<>();

	public UsuarioFullModel() {
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public EnderecoModel getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoModel endereco) {
		this.endereco = endereco;
	}

	public List<FeedbackModel> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<FeedbackModel> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public List<AgendamentoVacinaModel> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<AgendamentoVacinaModel> agendamentos) {
		this.agendamentos = agendamentos;
	}

}
