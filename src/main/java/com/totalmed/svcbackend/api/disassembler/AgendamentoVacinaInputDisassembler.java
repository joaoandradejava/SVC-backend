package com.totalmed.svcbackend.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.totalmed.svcbackend.api.input.AgendamentoVacinaInput;
import com.totalmed.svcbackend.domain.model.AgendamentoVacina;

@Component
public class AgendamentoVacinaInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public AgendamentoVacina toDomainModel(AgendamentoVacinaInput agendamentoVacinaInput) {
		return modelMapper.map(agendamentoVacinaInput, AgendamentoVacina.class);
	}

}
