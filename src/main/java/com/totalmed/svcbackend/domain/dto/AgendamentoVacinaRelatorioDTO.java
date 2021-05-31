package com.totalmed.svcbackend.domain.dto;

import java.time.LocalDateTime;

public class AgendamentoVacinaRelatorioDTO {
	private final Long id;
	private final String dataDoAgendamento;
	private final String vacina;

	public AgendamentoVacinaRelatorioDTO(Long id, LocalDateTime dataDoAgendamento, String vacina) {
		super();
		this.id = id;
		this.dataDoAgendamento = dataDoAgendamento.toString();
		this.vacina = vacina;
	}

	public Long getId() {
		return id;
	}

	public String getDataDoAgendamento() {
		return dataDoAgendamento;
	}

	public String getVacina() {
		return vacina;
	}

}
