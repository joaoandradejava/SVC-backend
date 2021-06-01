package com.totalmed.svcbackend.domain.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.totalmed.svcbackend.domain.model.enumeration.Perfil;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	private String senha;
	private Boolean isAtivo = Boolean.TRUE;

	@Embedded
	private Endereco endereco;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "perfil")
	@Enumerated(EnumType.STRING)
	private Set<Perfil> perfis = new HashSet<>();

	@OneToMany(mappedBy = "usuario", orphanRemoval = true)
	private List<Feedback> feedbacks = new ArrayList<>();

	@OneToMany(mappedBy = "usuario", orphanRemoval = true)
	private List<AgendamentoVacina> agendamentos = new ArrayList<>();

	@OneToOne
	private CarteiraVacinacao carteiraVacinacao;

	public Usuario() {
	}

	public Usuario(Long id, String nome, String cpf, String telefone, String email, String senha, Boolean isAtivo,
			Endereco endereco) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
		this.isAtivo = isAtivo;
		this.endereco = endereco;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public List<AgendamentoVacina> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<AgendamentoVacina> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public CarteiraVacinacao getCarteiraVacinacao() {
		return carteiraVacinacao;
	}

	public void setCarteiraVacinacao(CarteiraVacinacao carteiraVacinacao) {
		this.carteiraVacinacao = carteiraVacinacao;
	}

	public void adicionarPerfil(Perfil perfil) {
		this.perfis.add(perfil);
	}

	public void removerPerfil(Perfil perfil) {
		this.perfis.remove(perfil);
	}

	public boolean isAdmin() {
		return this.perfis.contains(Perfil.ADMIN);
	}

	public void adicionarEndereco(Endereco endereco) {
		setEndereco(endereco);
	}

	public void removerEndereco() {
		setEndereco(null);
	}

	public void ativar() {
		setIsAtivo(true);
	}

	public void desativar() {
		setIsAtivo(false);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
