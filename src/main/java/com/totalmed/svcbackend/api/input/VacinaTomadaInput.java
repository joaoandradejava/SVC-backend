package com.totalmed.svcbackend.api.input;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class VacinaTomadaInput {

	@Size(max = 255)
	@NotBlank
	private String nome;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	@DateTimeFormat(pattern = "dd/MM/yyyy", iso = ISO.DATE)
	private LocalDate data;

	public VacinaTomadaInput() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

}
