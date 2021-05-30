package com.totalmed.svcbackend.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.totalmed.svcbackend.domain.model.AgendamentoVacina;
import com.totalmed.svcbackend.domain.repository.AgendamentoVacinaRepository;

@Service
public class AgendamentoVacinaService {

	@Autowired
	private AgendamentoVacinaRepository repository;

	public Page<AgendamentoVacina> buscarTodosAgendamentosDeVacinasQueNaoForamTomadasPorPaginacao(Pageable pageable) {
		return repository.buscarTodosAgendamentosDeVacinasQueNaoForamTomadasPorPaginacao(pageable);
	}

}
