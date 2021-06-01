package com.totalmed.svcbackend.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CarteiraVacinacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	@OneToMany(mappedBy = "carteiraVacinacao", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<VacinaTomada> vacinasTomadas = new ArrayList<>();

	@OneToOne(mappedBy = "carteiraVacinacao")
	private Usuario usuario;

	public CarteiraVacinacao() {
	}

	public CarteiraVacinacao(Long id, String nome, List<VacinaTomada> vacinasTomadas, Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.vacinasTomadas = vacinasTomadas;
		this.usuario = usuario;
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

	public List<VacinaTomada> getVacinasTomadas() {
		return vacinasTomadas;
	}

	public void setVacinasTomadas(List<VacinaTomada> vacinasTomadas) {
		this.vacinasTomadas = vacinasTomadas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void adicionarVacinaTomada(VacinaTomada vacinaTomada) {
		getVacinasTomadas().add(vacinaTomada);
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
		CarteiraVacinacao other = (CarteiraVacinacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
