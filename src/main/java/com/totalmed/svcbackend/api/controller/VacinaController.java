package com.totalmed.svcbackend.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.totalmed.svcbackend.api.assembler.VacinaModelAssembler;
import com.totalmed.svcbackend.api.model.VacinaModel;
import com.totalmed.svcbackend.domain.model.Vacina;
import com.totalmed.svcbackend.domain.service.VacinaService;

@RestController
@RequestMapping("/vacinas")
public class VacinaController {

	@Autowired
	private VacinaService service;

	@Autowired
	private VacinaModelAssembler vacinaModelAssembler;

	@GetMapping
	public List<VacinaModel> buscarTodas() {
		List<Vacina> lista = service.buscarTodas();

		return vacinaModelAssembler.toCollectionModel(lista);
	}
}
