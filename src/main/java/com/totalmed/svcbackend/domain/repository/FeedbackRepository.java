package com.totalmed.svcbackend.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.totalmed.svcbackend.domain.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

	@Query("select f from Feedback f where f.id = ?2 and f.usuario.id = ?1")
	Optional<Feedback> buscarFeedbackDoUsuario(Long usuarioId, Long feedbackId);
}
