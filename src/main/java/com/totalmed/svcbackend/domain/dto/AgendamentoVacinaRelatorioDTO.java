package com.totalmed.svcbackend.domain.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class AgendamentoVacinaRelatorioDTO {
	private final Long id;
	private final Date dataDoAgendamento;
	private final String vacina;

	public AgendamentoVacinaRelatorioDTO(Long id, LocalDateTime dataDoAgendamento, String vacina) {
		this.id = id;
		this.dataDoAgendamento = Date.from(dataDoAgendamento.atZone(ZoneId.systemDefault()).toInstant());
		this.vacina = vacina;
	}

	public Long getId() {
		return id;
	}

	public Date getDataDoAgendamento() {
		return dataDoAgendamento;
	}

	public String getVacina() {
		return vacina;
	}

}
