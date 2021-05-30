package com.totalmed.svcbackend.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.totalmed.svcbackend.api.model.UsuarioFullModel;
import com.totalmed.svcbackend.domain.model.Usuario;

@Component
public class UsuarioFullModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public UsuarioFullModel toModel(Usuario usuario) {
		UsuarioFullModel usuarioFullModel = modelMapper.map(usuario, UsuarioFullModel.class);
		usuarioFullModel.setIsAdmin(usuario.isAdmin());

		return usuarioFullModel;
	}

	public List<UsuarioFullModel> toCollectionModel(List<Usuario> lista) {
		return lista.stream().map(usuario -> toModel(usuario)).collect(Collectors.toList());
	}
}
