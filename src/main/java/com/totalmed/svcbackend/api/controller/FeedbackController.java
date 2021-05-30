package com.totalmed.svcbackend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.totalmed.svcbackend.api.assembler.FeedbackDetalheModelAssembler;
import com.totalmed.svcbackend.api.model.FeedbackDetalheModel;
import com.totalmed.svcbackend.domain.model.Feedback;
import com.totalmed.svcbackend.domain.service.FeedbackService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Feedback Controller - ADMIN")
@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@Autowired
	private FeedbackDetalheModelAssembler feedbackDetalheModelAssembler;

	@Operation(summary = "Busca todos os feedbacks feitos pelos usuarios de forma paginada", description = "Busca todos os feedbacks feitos pelos usuarios de forma paginada, ordenado pelos feedback mais novos por padrão")
	@GetMapping("/paginacao")
	@PreAuthorize("hasRole('ADMIN')")
	public Page<FeedbackDetalheModel> buscarTodosPorPaginacao(
			@PageableDefault(sort = "id", direction = Direction.DESC) Pageable pageable) {
		Page<Feedback> page = feedbackService.buscarTodosPorPaginacao(pageable);

		return page.map(feedback -> feedbackDetalheModelAssembler.toModel(feedback));
	}

	@Operation(summary = "Busca um feedback pelo id", description = "Busca um feedback pelo id")
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public FeedbackDetalheModel buscarPorId(@PathVariable Long id) {
		Feedback feedback = feedbackService.buscarPorId(id);

		return feedbackDetalheModelAssembler.toModel(feedback);
	}

	@Operation(summary = "Deleta um feedback pelo id", description = "Deleta um feedback pelo id")
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ADMIN')")
	public void deletarPorId(@PathVariable Long id) {
		feedbackService.deletarPorId(id);
	}
}
