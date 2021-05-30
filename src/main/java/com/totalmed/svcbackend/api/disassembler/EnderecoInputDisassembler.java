package com.totalmed.svcbackend.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.totalmed.svcbackend.api.input.EnderecoInput;
import com.totalmed.svcbackend.domain.model.Endereco;

@Component
public class EnderecoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Endereco toDomainModel(EnderecoInput enderecoInput) {
		return modelMapper.map(enderecoInput, Endereco.class);
	}

}
