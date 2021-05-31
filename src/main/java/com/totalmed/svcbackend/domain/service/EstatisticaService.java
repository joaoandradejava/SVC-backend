package com.totalmed.svcbackend.domain.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totalmed.svcbackend.domain.dto.AgendamentoVacinaRelatorioDTO;
import com.totalmed.svcbackend.domain.exception.ErroInternoNoServidorException;
import com.totalmed.svcbackend.domain.repository.AgendamentoVacinaRepository;
import com.totalmed.svcbackend.domain.service.crud.CadastroUsuarioService;
import com.totalmed.svcbackend.domain.service.report.RelatorioService;

@Service
public class EstatisticaService {

	@Autowired
	private AgendamentoVacinaRepository agendamentoVacinaRepository;

	@Autowired
	private RelatorioService relatorioService;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	public String gerarRelatorioDosAgendamentosDoUsuario(Long usuarioId) {
		cadastroUsuarioService.buscarPorId(usuarioId);

		try {
			List<AgendamentoVacinaRelatorioDTO> lista = agendamentoVacinaRepository
					.buscarTodosAgendamentosDoUsuario(usuarioId);
			String relatorioEmBase64 = relatorioService.gerarRelatorio("agendamento-vacinas", new HashMap<>(), lista);

			return relatorioEmBase64;
		} catch (Exception e) {
			throw new ErroInternoNoServidorException(
					"Aconteceu um erro inesperado na tentativa de geração do relatorio dos agendamentos das vacinas do usuario");
		}
	}
}
