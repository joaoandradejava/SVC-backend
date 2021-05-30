package com.totalmed.svcbackend.api.model;

import java.time.LocalDate;

public class FeedbackModel {
	private Long id;
	private String comentario;
	private LocalDate data;

	public FeedbackModel() {
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

}
