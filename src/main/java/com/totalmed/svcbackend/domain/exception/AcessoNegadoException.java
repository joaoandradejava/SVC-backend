package com.totalmed.svcbackend.domain.exception;

public class AcessoNegadoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public AcessoNegadoException(String mensagem) {
		super(mensagem);
	}

}
