package com.totalmed.svcbackend.domain.exception;

public class CarteiraVacinacaoNaoEncontradaException extends RecursoNaoEncontradoException {

	private static final long serialVersionUID = 1L;

	public CarteiraVacinacaoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public CarteiraVacinacaoNaoEncontradaException(Long id) {
		super(String.format("A Carteira de vacinação de id %d não foi encontrada no sistema!", id));
	}
}
