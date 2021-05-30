package com.totalmed.svcbackend.api.input;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AgendamentoVacinaInput {

	@NotNull
	@Future
	private LocalDateTime dataAgendamento;

	@Size(max = 255)
	@NotBlank
	private String tipoVacina;

	public AgendamentoVacinaInput() {
	}

	public LocalDateTime getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDateTime dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getTipoVacina() {
		return tipoVacina;
	}

	public void setTipoVacina(String tipoVacina) {
		this.tipoVacina = tipoVacina;
	}

}
