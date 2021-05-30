package com.totalmed.svcbackend.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.totalmed.svcbackend.api.model.AgendamentoVacinaModel;
import com.totalmed.svcbackend.domain.model.AgendamentoVacina;

@Component
public class AgendamentoVacinaModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public AgendamentoVacinaModel toModel(AgendamentoVacina agendamentoVacina) {
		return modelMapper.map(agendamentoVacina, AgendamentoVacinaModel.class);
	}

	public List<AgendamentoVacinaModel> toCollectionModel(List<AgendamentoVacina> lista) {
		return lista.stream().map(agendamentoVacina -> toModel(agendamentoVacina)).collect(Collectors.toList());
	}
}
