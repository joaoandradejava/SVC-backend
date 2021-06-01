package com.totalmed.svcbackend.api.model;

import java.util.ArrayList;
import java.util.List;

public class CarteiraVacinacaoModel {
	private Long id;
	private String nome;
	private List<VacinaTomadaModel> vacinasTomadas = new ArrayList<>();

	public CarteiraVacinacaoModel() {
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

	public List<VacinaTomadaModel> getVacinasTomadas() {
		return vacinasTomadas;
	}

	public void setVacinasTomadas(List<VacinaTomadaModel> vacinasTomadas) {
		this.vacinasTomadas = vacinasTomadas;
	}

}
