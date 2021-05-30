package com.totalmed.svcbackend.api.model;

import java.time.LocalDateTime;

public class AgendamentoVacinaModel {
	private Long id;
	private LocalDateTime dataAgendamento;
	private Boolean isVacinaTomada;
	private String tipoVacina;

	public AgendamentoVacinaModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDateTime dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public Boolean getIsVacinaTomada() {
		return isVacinaTomada;
	}

	public void setIsVacinaTomada(Boolean isVacinaTomada) {
		this.isVacinaTomada = isVacinaTomada;
	}

	public String getTipoVacina() {
		return tipoVacina;
	}

	public void setTipoVacina(String tipoVacina) {
		this.tipoVacina = tipoVacina;
	}

}
