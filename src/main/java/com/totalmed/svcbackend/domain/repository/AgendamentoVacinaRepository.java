package com.totalmed.svcbackend.domain.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.totalmed.svcbackend.domain.model.AgendamentoVacina;

@Repository
public interface AgendamentoVacinaRepository extends JpaRepository<AgendamentoVacina, Long> {

	@Query("select a from AgendamentoVacina a where a.usuario.id = ?2 and DATE(a.dataAgendamento) = DATE(?1)")
	Optional<AgendamentoVacina> buscarVacinaAgendadaPeloUsuarioNoMesmoDia(LocalDateTime dataAgendamento,
			Long usuarioId);

	@Query("select a from AgendamentoVacina a where a.id = ?2 and a.usuario.id = ?1")
	Optional<AgendamentoVacina> buscarAgendamentoDeVacinaDoUsuario(Long usuarioId, Long agendamentoVacinaId);

	@Query("select a from AgendamentoVacina a where a.usuario.id = ?1")
	Page<AgendamentoVacina> buscarTodosAgendamentoDoUsuarioPorPaginacao(Long usuarioId, Pageable pageable);

	@Query("select a from AgendamentoVacina a where a.isVacinaTomada = false")
	Page<AgendamentoVacina> buscarTodosAgendamentosDeVacinasQueNaoForamTomadasPorPaginacao(Pageable pageable);

}
