package com.totalmed.svcbackend.domain.service.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.totalmed.svcbackend.domain.exception.NegocioException;
import com.totalmed.svcbackend.domain.model.AgendamentoVacina;
import com.totalmed.svcbackend.domain.repository.AgendamentoVacinaRepository;

@Component
public class DataAgendamentoVacinaValidator {

	@Autowired
	private AgendamentoVacinaRepository repository;

	public void verificarSeDataAgendamentoVacinaEValido(AgendamentoVacina agendamentoVacina) {
		Optional<AgendamentoVacina> obj = repository.buscarVacinaAgendadaPeloUsuarioNoMesmoDia(
				agendamentoVacina.getDataAgendamento(), agendamentoVacina.getUsuario().getId());

		if (obj.isPresent() && !obj.get().equals(agendamentoVacina)) {
			throw new NegocioException(String.format(
					"Você já tem uma vacina agendada para este dia, tente agendar uma nova vacina para outro dia!"));
		}
	}
}
