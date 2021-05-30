package com.totalmed.svcbackend.domain.exception;

public class FeedbackNaoEncontradoException extends RecursoNaoEncontradoException {

	private static final long serialVersionUID = 1L;

	public FeedbackNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public FeedbackNaoEncontradoException(Long id) {
		super(String.format("O Feedback de id %d não foi encontrado no sistema!", id));
	}
}
