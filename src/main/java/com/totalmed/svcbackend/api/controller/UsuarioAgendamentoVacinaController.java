package com.totalmed.svcbackend.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.totalmed.svcbackend.api.assembler.AgendamentoVacinaModelAssembler;
import com.totalmed.svcbackend.api.disassembler.AgendamentoVacinaInputDisassembler;
import com.totalmed.svcbackend.api.input.AgendamentoVacinaInput;
import com.totalmed.svcbackend.api.model.AgendamentoVacinaModel;
import com.totalmed.svcbackend.core.security.UsuarioAutenticado;
import com.totalmed.svcbackend.domain.exception.AcessoNegadoException;
import com.totalmed.svcbackend.domain.model.AgendamentoVacina;
import com.totalmed.svcbackend.domain.service.PermissaoService;
import com.totalmed.svcbackend.domain.service.UsuarioAgendamentoVacinaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuario Agendamento Vacina Controller")
@RestController
@RequestMapping("/usuarios/{usuarioId}/agendamentos")
public class UsuarioAgendamentoVacinaController {

	@Autowired
	private AgendamentoVacinaInputDisassembler agendamentoVacinaInputDisassembler;

	@Autowired
	private AgendamentoVacinaModelAssembler agendamentoVacinaModelAssembler;

	@Autowired
	private UsuarioAgendamentoVacinaService agendamentoVacinaService;

	@Autowired
	private PermissaoService permissaoService;

	@Operation(summary = "Busca todos os agendamentos feitos pelo usuario de forma paginada", description = "Busca todos os agendamentos feitos pelo usuario de forma paginada")
	@GetMapping("/paginacao")
	public Page<AgendamentoVacinaModel> buscarTodosPorPaginacao(@PathVariable Long usuarioId,
			@PageableDefault(sort = "id", direction = Direction.DESC) Pageable pageable,
			@AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
		if (permissaoService.isNaoTemPermissao(usuarioAutenticado, usuarioId)) {
			throw new AcessoNegadoException(
					String.format("Você não tem permissão para acessar o agendamento de vacinas de outros usuarios"));
		}

		Page<AgendamentoVacina> page = agendamentoVacinaService.buscarTodosPorPaginacao(usuarioId, pageable);

		return page.map(agendamento -> agendamentoVacinaModelAssembler.toModel(agendamento));
	}

	@Operation(summary = "Agenda uma vacina", description = "Agenda uma vacina, porém o usuario não poderá agendar mais de uma vacina no mesmo dia")
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public AgendamentoVacinaModel agendarVacina(@Valid @RequestBody AgendamentoVacinaInput agendamentoVacinaInput,
			@PathVariable Long usuarioId, @AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
		if (permissaoService.isNaoTemPermissao(usuarioAutenticado, usuarioId)) {
			throw new AcessoNegadoException(
					String.format("Você não tem permissão para agendar as vacinas de outro usuario!"));
		}

		AgendamentoVacina agendamentoVacina = agendamentoVacinaService
				.agendarVacina(agendamentoVacinaInputDisassembler.toDomainModel(agendamentoVacinaInput), usuarioId);

		return agendamentoVacinaModelAssembler.toModel(agendamentoVacina);
	}

	@Operation(summary = "Desmarca uma vacina já agendada", description = "Desmarca uma vacina já agendada, porém não sera possivel desmarcar uma vacina que já foi tomada")
	@DeleteMapping("/{agendamentoVacinaId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void desmarcarVacina(@PathVariable Long usuarioId, @PathVariable Long agendamentoVacinaId,
			@AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
		if (permissaoService.isNaoTemPermissao(usuarioAutenticado, usuarioId)) {
			throw new AcessoNegadoException(String
					.format("Você não tem permissão para desmarcar o agendamento da vacina de um outro usuario!"));
		}

		agendamentoVacinaService.desmarcarVacina(usuarioId, agendamentoVacinaId);
	}

}
