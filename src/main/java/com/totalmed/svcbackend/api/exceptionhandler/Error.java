package com.totalmed.svcbackend.api.exceptionhandler;

public enum Error {
	NEGOCIO("negocio", "Ocorreu um erro do lado do client-side(Frontend)"),
	RECURSO_NAO_ENCONTRADO("recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("entidade-em-uso", "Entidade em uso"), ERRO_VALIDACAO("erro-validacao", "Erro de validação"),
	ERRO_NA_DESSERIALIZACAO_DO_JSON("erro-na-desserializacao-do-json", "Erro na desserializacao do JSON"),
	PROPRIEDADE_NAO_RECONHECIDA("propriedade-nao-reconhecida", "Propriedade não reconhecida"),
	METODO_DE_REQUISICAO_NAO_SUPORTADO("metodo-requisicao-nao-suportado", "Método de requisição não suportado"),
	ENDPOINT_NAO_ENCONTRADO("endpoint-nao-encontrado", "Endpoint não encontrado"),
	TIPO_INCOPATIVEL("tipo-incopativel", "Tipo incopatível"),
	ERRO_INTERNO_NO_SERVIDOR("erro-interno-no-servidor", "Erro interno no servidor"),
	ERRO_NA_AUTENTICACAO("erro-autenticacao", "Erro ao tentar se autenticar"),
	ACESSO_NEGADO("acesso-negado", "Acesso negado");

	private String type;
	private String title;

	private Error(String type, String title) {
		this.type = "https://www.totalmed.com.br/" + type;
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}
}
