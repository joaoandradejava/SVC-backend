package com.totalmed.svcbackend.api.input;

import javax.validation.constraints.NotBlank;

public class FeedbackInput {

	@NotBlank
	private String comentario;

	public FeedbackInput() {
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}
