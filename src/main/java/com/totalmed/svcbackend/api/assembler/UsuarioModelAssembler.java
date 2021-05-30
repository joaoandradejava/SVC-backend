package com.totalmed.svcbackend.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.totalmed.svcbackend.api.model.UsuarioModel;
import com.totalmed.svcbackend.domain.model.Usuario;

@Component
public class UsuarioModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public UsuarioModel toModel(Usuario usuario) {
		UsuarioModel usuarioModel = modelMapper.map(usuario, UsuarioModel.class);
		usuarioModel.setIsAdmin(usuario.isAdmin());

		return usuarioModel;
	}

	public List<UsuarioModel> toCollectionModel(List<Usuario> lista) {
		return lista.stream().map(usuario -> toModel(usuario)).collect(Collectors.toList());
	}
}
