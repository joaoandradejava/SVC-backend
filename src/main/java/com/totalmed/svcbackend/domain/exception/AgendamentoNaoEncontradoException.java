package com.totalmed.svcbackend.domain.exception;

public class AgendamentoNaoEncontradoException extends RecursoNaoEncontradoException {

	private static final long serialVersionUID = 1L;

	public AgendamentoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public AgendamentoNaoEncontradoException(Long id) {
		super(String.format("O Agendamento de vacina de id %d não foi encontrado no sistema!", id));
	}
}
