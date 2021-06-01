package com.totalmed.svcbackend.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.totalmed.svcbackend.domain.model.CarteiraVacinacao;

@Repository
public interface CarteiraVacinacaoRepository extends JpaRepository<CarteiraVacinacao, Long> {

	@Query("select c from CarteiraVacinacao c where c.usuario.id = ?1")
	Optional<CarteiraVacinacao> buscarCarteiraDoUsuario(Long usuarioId);
}
