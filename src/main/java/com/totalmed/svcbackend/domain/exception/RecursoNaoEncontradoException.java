package com.totalmed.svcbackend.domain.exception;

public class RecursoNaoEncontradoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public RecursoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}
