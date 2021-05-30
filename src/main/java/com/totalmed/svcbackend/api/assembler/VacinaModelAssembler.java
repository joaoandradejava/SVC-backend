package com.totalmed.svcbackend.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.totalmed.svcbackend.api.model.VacinaModel;
import com.totalmed.svcbackend.domain.model.Vacina;

@Component
public class VacinaModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public VacinaModel toModel(Vacina vacina) {
		return modelMapper.map(vacina, VacinaModel.class);
	}

	public List<VacinaModel> toCollectionModel(List<Vacina> lista) {
		return lista.stream().map(vacina -> toModel(vacina)).collect(Collectors.toList());
	}
}
