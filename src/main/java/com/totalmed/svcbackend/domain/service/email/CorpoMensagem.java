package com.totalmed.svcbackend.domain.service.email;

public class CorpoMensagem {
	private final String nomeHtmlMail;
	private final Object objeto;

	public CorpoMensagem(String nomeHtmlMail, Object objeto) {
		this.nomeHtmlMail = nomeHtmlMail;
		this.objeto = objeto;
	}

	public String getNomeHtmlMail() {
		return nomeHtmlMail;
	}

	public Object getObjeto() {
		return objeto;
	}

}
