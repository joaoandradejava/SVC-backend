package com.totalmed.svcbackend.domain.exception;

public class UsuarioNaoEncontradoException extends RecursoNaoEncontradoException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public UsuarioNaoEncontradoException(Long id) {
		super(String.format("O Usuario de id %d não foi encontrado no sistema!", id));
	}
}
