package com.totalmed.svcbackend.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.totalmed.svcbackend.api.input.UsuarioCreateInput;
import com.totalmed.svcbackend.domain.model.Usuario;

@Component
public class UsuarioCreateInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Usuario toDomainModel(UsuarioCreateInput usuarioCreateInput) {
		return modelMapper.map(usuarioCreateInput, Usuario.class);
	}

}
