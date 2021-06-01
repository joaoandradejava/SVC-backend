package com.totalmed.svcbackend.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.totalmed.svcbackend.api.input.VacinaTomadaInput;
import com.totalmed.svcbackend.domain.model.VacinaTomada;

@Component
public class VacinaTomadaInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public VacinaTomada toDomainModel(VacinaTomadaInput vacinaTomadaInput) {
		return modelMapper.map(vacinaTomadaInput, VacinaTomada.class);
	}

}
