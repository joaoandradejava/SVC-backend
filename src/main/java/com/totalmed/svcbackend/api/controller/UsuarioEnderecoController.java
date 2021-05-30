package com.totalmed.svcbackend.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.totalmed.svcbackend.api.disassembler.EnderecoInputDisassembler;
import com.totalmed.svcbackend.api.input.EnderecoInput;
import com.totalmed.svcbackend.core.security.UsuarioAutenticado;
import com.totalmed.svcbackend.domain.exception.AcessoNegadoException;
import com.totalmed.svcbackend.domain.exception.EstadoNaoEncontradoException;
import com.totalmed.svcbackend.domain.exception.NegocioException;
import com.totalmed.svcbackend.domain.service.PermissaoService;
import com.totalmed.svcbackend.domain.service.crud.CadastroUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuario Endereco Controller")
@RestController
@RequestMapping("/usuarios/{usuarioId}/endereco")
public class UsuarioEnderecoController {

	@Autowired
	private EnderecoInputDisassembler enderecoInputDisassembler;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private PermissaoService permissaoService;

	@Operation(summary = "Adiciona o endereço ao usuario", description = "Adiciona o endereço ao usuario")
	@PutMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void adicionarEndereco(@Valid @RequestBody EnderecoInput enderecoInput, @PathVariable Long usuarioId,
			@AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
		if (permissaoService.isNaoTemPermissao(usuarioAutenticado, usuarioId)) {
			throw new AcessoNegadoException(
					String.format("Você não tem permissão para adiciar um endereço a um outro usuario"));
		}

		try {
			cadastroUsuarioService.adicionarEndereco(enderecoInputDisassembler.toDomainModel(enderecoInput), usuarioId);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@Operation(summary = "Remove o endereço do usuario", description = "Remove o endereço do usuario")
	@DeleteMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removerEndereco(@PathVariable Long usuarioId,
			@AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
		if (permissaoService.isNaoTemPermissao(usuarioAutenticado, usuarioId)) {
			throw new AcessoNegadoException(
					String.format("Você não tem permissão de remover o endereço de outro usuario!"));
		}

		cadastroUsuarioService.removerEndereco(usuarioId);
	}

}
