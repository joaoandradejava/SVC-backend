package com.totalmed.svcbackend.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.totalmed.svcbackend.domain.exception.AgendamentoNaoEncontradoException;
import com.totalmed.svcbackend.domain.exception.EntidadeEmUsoException;
import com.totalmed.svcbackend.domain.exception.NegocioException;
import com.totalmed.svcbackend.domain.model.AgendamentoVacina;
import com.totalmed.svcbackend.domain.model.Usuario;
import com.totalmed.svcbackend.domain.repository.AgendamentoVacinaRepository;
import com.totalmed.svcbackend.domain.service.crud.CadastroUsuarioService;
import com.totalmed.svcbackend.domain.service.validator.DataAgendamentoVacinaValidator;

@Service
public class UsuarioAgendamentoVacinaService {

	@Autowired
	private AgendamentoVacinaRepository repository;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private DataAgendamentoVacinaValidator dataAgendamentoVacinaValidator;

	public Page<AgendamentoVacina> buscarTodosPorPaginacao(Long usuarioId, Pageable pageable) {
		return repository.buscarTodosAgendamentoDoUsuarioPorPaginacao(usuarioId, pageable);
	}

	public AgendamentoVacina buscarPorId(Long agendamentoVacinaId) {
		return repository.findById(agendamentoVacinaId)
				.orElseThrow(() -> new AgendamentoNaoEncontradoException(agendamentoVacinaId));
	}

	public AgendamentoVacina buscarAgendamentoDeVacinaDoUsuario(Long usuarioId, Long agendamentoVacinaId) {
		cadastroUsuarioService.buscarPorId(usuarioId);
		buscarPorId(agendamentoVacinaId);

		return repository.buscarAgendamentoDeVacinaDoUsuario(usuarioId, agendamentoVacinaId)
				.orElseThrow(() -> new NegocioException(
						String.format("O Agendamento da vacina de id %d não estar associada com o Usuario de id %d",
								agendamentoVacinaId, usuarioId)));
	}

	@Transactional
	public AgendamentoVacina agendarVacina(AgendamentoVacina agendamentoVacina, Long usuarioId) {
		Usuario usuario = cadastroUsuarioService.buscarPorId(usuarioId);
		agendamentoVacina.setUsuario(usuario);
		dataAgendamentoVacinaValidator.verificarSeDataAgendamentoVacinaEValido(agendamentoVacina);

		return repository.save(agendamentoVacina);
	}

	@Transactional
	public void desmarcarVacina(Long usuarioId, Long agendamentoVacinaId) {
		AgendamentoVacina agendamentoVacina = buscarAgendamentoDeVacinaDoUsuario(usuarioId, agendamentoVacinaId);

		if (agendamentoVacina.getIsVacinaTomada() == true) {
			throw new NegocioException(
					String.format("Não é possivel desmarcar o Agendamento de uma vacina que já foi tomada!"));
		}

		try {
			repository.deleteById(agendamentoVacina.getId());
			repository.flush();

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(
					"Não foi possivel desmarcar o Agendamento da vacina de id %d, pois ela estar em uso no sistema!",
					agendamentoVacinaId));
		}

	}

}
