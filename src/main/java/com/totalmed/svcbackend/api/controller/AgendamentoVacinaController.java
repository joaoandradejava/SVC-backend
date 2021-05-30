package com.totalmed.svcbackend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.totalmed.svcbackend.api.assembler.AgendamentoVacinaModelAssembler;
import com.totalmed.svcbackend.api.model.AgendamentoVacinaModel;
import com.totalmed.svcbackend.domain.model.AgendamentoVacina;
import com.totalmed.svcbackend.domain.service.AgendamentoVacinaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Agendamento Vacina Controller - ADMIN")
@RestController
@RequestMapping("/agendamentos")
public class AgendamentoVacinaController {

	@Autowired
	private AgendamentoVacinaService agendamentoVacinaService;

	@Autowired
	private AgendamentoVacinaModelAssembler agendamentoVacinaModelAssembler;

	@Operation(summary = "Busca todos os agendamentos de vacinas que não foram tomadas", description = "Busca todos os agendamentos de vacinas que não foram tomadas")
	@GetMapping("/nao-tomadas/paginacao")
	@PreAuthorize("hasRole('ADMIN')")
	public Page<AgendamentoVacinaModel> buscarTodosAgendamentosDeVacinasQueNaoForamTomadasPorPaginacao(
			Pageable pageable) {
		Page<AgendamentoVacina> page = agendamentoVacinaService
				.buscarTodosAgendamentosDeVacinasQueNaoForamTomadasPorPaginacao(pageable);

		return page.map(agendamento -> agendamentoVacinaModelAssembler.toModel(agendamento));
	}
}
