package com.totalmed.svcbackend.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.totalmed.svcbackend.api.input.CarteiraVacinacaoCreateInput;
import com.totalmed.svcbackend.domain.model.CarteiraVacinacao;

@Component
public class CarteiraVacinacaoCreateInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public CarteiraVacinacao toDomainModel(CarteiraVacinacaoCreateInput carteiraVacinacaoCreateInput) {
		return modelMapper.map(carteiraVacinacaoCreateInput, CarteiraVacinacao.class);

	}

}
