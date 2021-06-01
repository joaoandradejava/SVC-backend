package com.totalmed.svcbackend.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.totalmed.svcbackend.api.assembler.CarteiraVacinacaoModelAssembler;
import com.totalmed.svcbackend.api.disassembler.CarteiraVacinacaoCreateInputDisassembler;
import com.totalmed.svcbackend.api.disassembler.VacinaTomadaInputDisassembler;
import com.totalmed.svcbackend.api.input.CarteiraVacinacaoCreateInput;
import com.totalmed.svcbackend.api.input.VacinaTomadaInput;
import com.totalmed.svcbackend.api.model.CarteiraVacinacaoModel;
import com.totalmed.svcbackend.domain.model.CarteiraVacinacao;
import com.totalmed.svcbackend.domain.service.CarteiraVacinacaoService;

@RestController
@RequestMapping("/usuarios/{usuarioId}/carteira")
public class CarteiraVacinacaoController {

	@Autowired
	private CarteiraVacinacaoCreateInputDisassembler carteiraVacinacaoCreateInputDisassembler;

	@Autowired
	private CarteiraVacinacaoService carteiraVacinacaoService;

	@Autowired
	private CarteiraVacinacaoModelAssembler carteiraVacinacaoModelAssembler;

	@Autowired
	private VacinaTomadaInputDisassembler vacinaTomadaInputDisassembler;

	@GetMapping
	public CarteiraVacinacaoModel buscarCarteiraDoUsuario(@PathVariable Long usuarioId) {
		CarteiraVacinacao carteiraVacinacao = carteiraVacinacaoService.buscarCarteiraDoUsuario(usuarioId);

		return carteiraVacinacaoModelAssembler.toModel(carteiraVacinacao);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public CarteiraVacinacaoModel inserirCarteira(
			@Valid @RequestBody CarteiraVacinacaoCreateInput carteiraVacinacaoCreateInput,
			@PathVariable Long usuarioId) {
		CarteiraVacinacao carteiraVacinacao = carteiraVacinacaoCreateInputDisassembler
				.toDomainModel(carteiraVacinacaoCreateInput);

		carteiraVacinacao = carteiraVacinacaoService.inserirCarteira(carteiraVacinacao, usuarioId);

		return carteiraVacinacaoModelAssembler.toModel(carteiraVacinacao);
	}

	@DeleteMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removerCarteira(@PathVariable Long usuarioId) {
		carteiraVacinacaoService.removerCarteiraDoUsuario(usuarioId);
	}

	@PostMapping("/vacina-tomada")
	public void adicionarVacinaTomada(@Valid @RequestBody VacinaTomadaInput vacinaTomadaInput,
			@PathVariable Long usuarioId) {
		carteiraVacinacaoService.adicionarVacinaTomada(vacinaTomadaInputDisassembler.toDomainModel(vacinaTomadaInput),
				usuarioId);
	}

}
