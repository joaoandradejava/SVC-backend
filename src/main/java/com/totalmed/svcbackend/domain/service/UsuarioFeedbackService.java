package com.totalmed.svcbackend.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.totalmed.svcbackend.domain.exception.EntidadeEmUsoException;
import com.totalmed.svcbackend.domain.exception.FeedbackNaoEncontradoException;
import com.totalmed.svcbackend.domain.exception.NegocioException;
import com.totalmed.svcbackend.domain.model.Feedback;
import com.totalmed.svcbackend.domain.model.Usuario;
import com.totalmed.svcbackend.domain.repository.FeedbackRepository;
import com.totalmed.svcbackend.domain.service.crud.CadastroUsuarioService;

@Service
public class UsuarioFeedbackService {

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private FeedbackRepository repository;

	public Feedback buscarPorId(Long feedbackId) {
		return repository.findById(feedbackId).orElseThrow(() -> new FeedbackNaoEncontradoException(feedbackId));
	}

	public Feedback buscarFeedbackDoUsuario(Long feedbackId, Long usuarioId) {
		cadastroUsuarioService.buscarPorId(usuarioId);
		buscarPorId(feedbackId);

		return repository.buscarFeedbackDoUsuario(usuarioId, feedbackId).orElseThrow(() -> new NegocioException(String
				.format("O Feedback de id %d não estar associado com o Usuario de id %d", feedbackId, usuarioId)));
	}

	@Transactional
	public Feedback enviarFeedback(Feedback feedback, Long usuarioId) {
		Usuario usuario = cadastroUsuarioService.buscarPorId(usuarioId);
		feedback.setUsuario(usuario);

		return repository.save(feedback);
	}

	@Transactional
	public void deletarFeedback(Long usuarioId, Long feedbackId) {
		Feedback feedback = buscarFeedbackDoUsuario(feedbackId, usuarioId);

		try {
			repository.deleteById(feedbackId);
			repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Não foi possivel excluir o feedback de id '%d', pois ele estar em uso no sistema!",
							feedback.getId()));
		}

	}

}
