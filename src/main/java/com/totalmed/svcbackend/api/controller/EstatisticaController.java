package com.totalmed.svcbackend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.totalmed.svcbackend.core.security.UsuarioAutenticado;
import com.totalmed.svcbackend.domain.dto.RelatorioDTO;
import com.totalmed.svcbackend.domain.service.EstatisticaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Estatistica Controller")
@RestController
@RequestMapping("/estatisticas")
public class EstatisticaController {

	@Autowired
	private EstatisticaService estatisticaService;

	@Operation(summary = "Gera relatorios dos agendamentos das vacinas do usuario", description = "Gera relatorios dos agendamentos das vacinas do usuario")
	@GetMapping("/agendamentos/relatorio")
	public RelatorioDTO gerarRelatorioDosAgendamentosDoUsuario(
			@AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
		String relatorioEmBase64 = estatisticaService
				.gerarRelatorioDosAgendamentosDoUsuario(usuarioAutenticado.getId());

		return new RelatorioDTO(relatorioEmBase64);
	}
}
