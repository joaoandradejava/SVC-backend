package com.totalmed.svcbackend.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.totalmed.svcbackend.domain.exception.EntidadeEmUsoException;
import com.totalmed.svcbackend.domain.exception.FeedbackNaoEncontradoException;
import com.totalmed.svcbackend.domain.model.Feedback;
import com.totalmed.svcbackend.domain.repository.FeedbackRepository;

@Service
public class FeedbackService {

	@Autowired
	private FeedbackRepository repository;

	public Page<Feedback> buscarTodosPorPaginacao(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Feedback buscarPorId(Long feedbackId) {
		return repository.findById(feedbackId).orElseThrow(() -> new FeedbackNaoEncontradoException(feedbackId));
	}

	@Transactional
	public void deletarPorId(Long id) {
		Feedback feedback = buscarPorId(id);

		try {
			repository.deleteById(id);
			repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Não foi possivel excluir o feedback de id '%d', pois ele estar em uso no sistema!",
							feedback.getId()));
		}

	}
}
