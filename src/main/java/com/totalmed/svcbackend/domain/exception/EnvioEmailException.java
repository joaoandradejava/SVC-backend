package com.totalmed.svcbackend.domain.exception;

public class EnvioEmailException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EnvioEmailException(String mensagem) {
		super(mensagem);
	}

}
