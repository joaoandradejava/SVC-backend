package com.totalmed.svcbackend.api.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EsqueciSenhaInput {

	@Size(max = 255)
	@NotBlank
	@Email
	private String email;

	public EsqueciSenhaInput() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
