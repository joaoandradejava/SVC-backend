package com.totalmed.svcbackend.domain.exception;

public class EstadoNaoEncontradoException extends RecursoNaoEncontradoException {

	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public EstadoNaoEncontradoException(Long id) {
		super(String.format("O Estado de id %d não foi encontrado no sistema!", id));
	}
}
