package com.totalmed.svcbackend.api.model;

import java.time.LocalDate;

public class FeedbackDetalheModel {
	private Long id;
	private String comentario;
	private LocalDate data;
	private UsuarioResumoModel usuario;

	public FeedbackDetalheModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public UsuarioResumoModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioResumoModel usuario) {
		this.usuario = usuario;
	}

}
