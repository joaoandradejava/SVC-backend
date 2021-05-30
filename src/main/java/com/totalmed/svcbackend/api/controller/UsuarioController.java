package com.totalmed.svcbackend.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.totalmed.svcbackend.api.assembler.UsuarioFullModelAssembler;
import com.totalmed.svcbackend.api.assembler.UsuarioModelAssembler;
import com.totalmed.svcbackend.api.disassembler.UsuarioCreateInputDisassembler;
import com.totalmed.svcbackend.api.disassembler.UsuarioUpdateInputDisassembler;
import com.totalmed.svcbackend.api.input.AlterarSenhaInput;
import com.totalmed.svcbackend.api.input.EsqueciSenhaInput;
import com.totalmed.svcbackend.api.input.UsuarioCreateInput;
import com.totalmed.svcbackend.api.input.UsuarioUpdateInput;
import com.totalmed.svcbackend.api.model.UsuarioFullModel;
import com.totalmed.svcbackend.api.model.UsuarioModel;
import com.totalmed.svcbackend.core.security.UsuarioAutenticado;
import com.totalmed.svcbackend.domain.exception.AcessoNegadoException;
import com.totalmed.svcbackend.domain.model.Usuario;
import com.totalmed.svcbackend.domain.observer.UsuarioCadastradoObserver;
import com.totalmed.svcbackend.domain.observer.UsuarioEsqueceuSenhaObserver;
import com.totalmed.svcbackend.domain.service.PermissaoService;
import com.totalmed.svcbackend.domain.service.UsuarioService;
import com.totalmed.svcbackend.domain.service.crud.CadastroUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuario Controller")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;

	@Autowired
	private UsuarioCreateInputDisassembler usuarioCreateInputDisassembler;

	@Autowired
	private UsuarioUpdateInputDisassembler usuarioUpdateInputDisassembler;

	@Autowired
	private UsuarioFullModelAssembler usuarioFullModelAssembler;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PermissaoService permissaoService;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Operation(summary = "Busca todos os usuarios - ADMIN", description = "Busca todos os usuarios - ADMIN")
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<UsuarioModel> buscarTodos() {
		List<Usuario> lista = cadastroUsuarioService.buscarTodos();

		return usuarioModelAssembler.toCollectionModel(lista);
	}

	@Operation(summary = "Busca todos os usuarios de forma paginada - ADMIN", description = "Busca todos os usuarios de forma paginada - ADMIN")
	@GetMapping("/paginacao")
	@PreAuthorize("hasRole('ADMIN')")
	public Page<UsuarioModel> buscarTodosPorPaginacao(@PageableDefault(size = 5) Pageable pageable, String nome) {
		Page<Usuario> page = null;

		if (StringUtils.hasLength(nome)) {
			page = cadastroUsuarioService.buscarTodosPorPaginacaoENome(pageable, nome);

			return page.map(usuario -> usuarioModelAssembler.toModel(usuario));
		}

		page = cadastroUsuarioService.buscarTodosPorPaginacao(pageable);
		return page.map(usuario -> usuarioModelAssembler.toModel(usuario));
	}

	@Operation(summary = "Busca os dados completos de um usuarios especifico pelo seu id", description = "Busca os dados completos de um usuarios especifico pelo seu id")
	@GetMapping("/{id}")
	public UsuarioFullModel buscarPorId(@PathVariable Long id,
			@AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
		if (permissaoService.isNaoTemPermissao(usuarioAutenticado, id)) {
			throw new AcessoNegadoException(
					String.format("Você não tem permissão para acessar os dados de um outro usuario!"));
		}

		Usuario usuario = cadastroUsuarioService.buscarPorId(id);

		return usuarioFullModelAssembler.toModel(usuario);
	}

	@Operation(summary = "Faz um cadastro de um novo usuario", description = "Faz um cadastro de um novo usuario no sistema e envia um email informando que o cadastro foi realizado com sucesso!")
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public UsuarioModel cadastrar(@Valid @RequestBody UsuarioCreateInput usuarioCreateInput) {
		Usuario usuario = cadastroUsuarioService
				.cadastrar(usuarioCreateInputDisassembler.toDomainModel(usuarioCreateInput));

		applicationEventPublisher.publishEvent(new UsuarioCadastradoObserver(usuario));
		return usuarioModelAssembler.toModel(usuario);
	}

	@Operation(summary = "Atualiza um cadastro já existente pelo seu id", description = "Atualiza um cadastro já existente pelo seu id")
	@PutMapping("/{id}")
	public UsuarioModel atualizar(@Valid @RequestBody UsuarioUpdateInput usuarioUpdateInput, @PathVariable Long id,
			@AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
		if (permissaoService.isNaoTemPermissao(usuarioAutenticado, id)) {
			throw new AcessoNegadoException(
					String.format("Você não tem permissão para atualizar os dados de um outro usuario!"));
		}

		Usuario usuario = cadastroUsuarioService.buscarPorId(id);
		usuarioUpdateInputDisassembler.copyToDomainModel(usuarioUpdateInput, usuario);
		usuario = cadastroUsuarioService.atualizar(usuario);

		return usuarioModelAssembler.toModel(usuario);
	}

	@Operation(summary = "Deleta um usuario pelo seu id", description = "Deleta um usuario pelo seu id")
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id, @AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
		if (permissaoService.isNaoTemPermissao(usuarioAutenticado, id)) {
			throw new AcessoNegadoException(
					String.format("Você não tem permissão para deletar o cadastro de um outro usuario!"));
		}

		cadastroUsuarioService.deletarPorId(id);
	}

	@Operation(summary = "Ativa a conta de um usuario pelo id", description = "Ativa a conta de um usuario pelo id")
	@PutMapping("/{id}/ativo")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long id, @AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
		if (permissaoService.isNaoTemPermissao(usuarioAutenticado, id)) {
			throw new AcessoNegadoException(
					String.format("Você não tem permissão para ativar a conta de um outro usuario!"));
		}

		usuarioService.ativar(id);
	}

	@Operation(summary = "Desativa conta de um usuario pelo seu id", description = "Desativa conta de um usuario pelo seu id")
	@DeleteMapping("/{id}/ativo")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void desativar(@PathVariable Long id, @AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
		if (permissaoService.isNaoTemPermissao(usuarioAutenticado, id)) {
			throw new AcessoNegadoException(
					String.format("Você não tem permissão para desativar a conta de um outro usuario!"));
		}

		usuarioService.desativar(id);
	}

	@Operation(summary = "Altera a senha", description = "Altera a senha do usuario")
	@PutMapping("/{id}/senha")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void alterarSenha(@Valid @RequestBody AlterarSenhaInput alterarSenhaInput, @PathVariable Long id,
			@AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
		if (permissaoService.isNaoTemPermissao(usuarioAutenticado, id)) {
			throw new AcessoNegadoException("Você não tem permissão para alterar a senha de outro usuario!");
		}

		usuarioService.alterarSenha(alterarSenhaInput.getSenhaAtual(), alterarSenhaInput.getNovaSenha(), id);
	}

	@Operation(summary = "Se o usuario tiver esquecido a senha, o sistema irá enviar umaa nova pra ele", description = "Se o usuario tiver esquecido a senha, o sistema irá enviar umaa nova pra ele por email")
	@PutMapping("/esqueci-senha")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void esqueciASenha(@Valid @RequestBody EsqueciSenhaInput esqueciSenhaInput) {
		String novaSenha = usuarioService.esqueciASenha(esqueciSenhaInput.getEmail());
		Usuario usuario = cadastroUsuarioService.buscarPorEmail(esqueciSenhaInput.getEmail());

		applicationEventPublisher.publishEvent(new UsuarioEsqueceuSenhaObserver(usuario, novaSenha));
	}

}
