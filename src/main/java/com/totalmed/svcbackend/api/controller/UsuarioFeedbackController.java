package com.totalmed.svcbackend.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.totalmed.svcbackend.api.assembler.FeedbackModelAssembler;
import com.totalmed.svcbackend.api.disassembler.FeedbackInputDisassembler;
import com.totalmed.svcbackend.api.input.FeedbackInput;
import com.totalmed.svcbackend.api.model.FeedbackModel;
import com.totalmed.svcbackend.core.security.UsuarioAutenticado;
import com.totalmed.svcbackend.domain.exception.AcessoNegadoException;
import com.totalmed.svcbackend.domain.model.Feedback;
import com.totalmed.svcbackend.domain.model.Usuario;
import com.totalmed.svcbackend.domain.service.PermissaoService;
import com.totalmed.svcbackend.domain.service.UsuarioFeedbackService;
import com.totalmed.svcbackend.domain.service.crud.CadastroUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuario Feedback Controller")
@RestController
@RequestMapping("/usuarios/{usuarioId}/feedbacks")
public class UsuarioFeedbackController {

	@Autowired
	private FeedbackModelAssembler feedbackModelAssembler;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private FeedbackInputDisassembler feedbackInputDisassembler;

	@Autowired
	private UsuarioFeedbackService feedbackService;

	@Autowired
	private PermissaoService permissaoService;

	@Operation(summary = "Busca todos os feedbacks do usuario", description = "Busca todos os feedbacks do usuario")
	@GetMapping
	public List<FeedbackModel> buscarTodos(@PathVariable Long usuarioId,
			@AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
		if (permissaoService.isNaoTemPermissao(usuarioAutenticado, usuarioId)) {
			throw new AcessoNegadoException(
					String.format("Você não tem permissão de buscar os feedbacks de outro usuario!"));
		}

		Usuario usuario = cadastroUsuarioService.buscarPorId(usuarioId);

		return feedbackModelAssembler.toCollectionModel(usuario.getFeedbacks());
	}

	@Operation(summary = "Faz com que o usuario envie um feedback", description = "Faz com que o usuario envie um feedback")
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public FeedbackModel enviarFeedback(@Valid @RequestBody FeedbackInput feedbackInput, @PathVariable Long usuarioId,
			@AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
		if (permissaoService.isNaoTemPermissao(usuarioAutenticado, usuarioId)) {
			throw new AcessoNegadoException(
					String.format("Você não tem permissão de enviar um feedback se passando por outro usuario"));
		}

		Feedback feedback = feedbackService.enviarFeedback(feedbackInputDisassembler.toDomainModel(feedbackInput),
				usuarioId);

		return feedbackModelAssembler.toModel(feedback);
	}

	@Operation(summary = "Deleta um feedback do usuario", description = "Deleta um feedback do usuario")
	@DeleteMapping("/{feedbackId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletarFeedback(@PathVariable Long usuarioId, @PathVariable Long feedbackId,
			@AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
		if (permissaoService.isNaoTemPermissao(usuarioAutenticado, usuarioId)) {
			throw new AcessoNegadoException(
					String.format("Você não tem permissão de deletar o feedback de outro usuario!"));
		}

		feedbackService.deletarFeedback(usuarioId, feedbackId);
	}

}
