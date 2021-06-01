package com.totalmed.svcbackend.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.totalmed.svcbackend.api.model.CarteiraVacinacaoModel;
import com.totalmed.svcbackend.domain.model.CarteiraVacinacao;

@Component
public class CarteiraVacinacaoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public CarteiraVacinacaoModel toModel(CarteiraVacinacao carteiraVacinacao) {
		return modelMapper.map(carteiraVacinacao, CarteiraVacinacaoModel.class);
	}

	public List<CarteiraVacinacaoModel> toCollectionModel(List<CarteiraVacinacao> lista) {
		return lista.stream().map(carteiraVacinacao -> toModel(carteiraVacinacao)).collect(Collectors.toList());
	}
}
