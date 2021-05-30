package com.totalmed.svcbackend.domain.service.crud;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.totalmed.svcbackend.domain.exception.EntidadeEmUsoException;
import com.totalmed.svcbackend.domain.exception.NegocioException;
import com.totalmed.svcbackend.domain.exception.UsuarioNaoEncontradoException;
import com.totalmed.svcbackend.domain.model.Endereco;
import com.totalmed.svcbackend.domain.model.Usuario;
import com.totalmed.svcbackend.domain.repository.UsuarioRepository;
import com.totalmed.svcbackend.domain.service.validator.CpfUniqueValidator;
import com.totalmed.svcbackend.domain.service.validator.EmailUniqueValidator;

@Service
public class CadastroUsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private CpfUniqueValidator cpfUniqueValidator;

	@Autowired
	private EmailUniqueValidator emailUniqueValidator;

	@Autowired
	private EntityManager entityManager;

	public List<Usuario> buscarTodos() {
		return repository.findAll();
	}

	public Page<Usuario> buscarTodosPorPaginacao(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Page<Usuario> buscarTodosPorPaginacaoENome(Pageable pageable, String nome) {
		return repository.buscarTodosPorPaginacaoENome(nome, pageable);
	}

	public Usuario buscarPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
	}

	public Usuario buscarPorEmail(String email) {
		return repository.findByEmail(email).orElseThrow(() -> new NegocioException(
				String.format("Não existe nenhum usuario com o email '%s' cadastrado no sistema!", email)));
	}

	@Transactional
	public Usuario cadastrar(Usuario usuario) {
		cpfUniqueValidator.verificarSeCpfEValido(usuario);
		emailUniqueValidator.verificarSeEmailEValido(usuario);
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

		return repository.save(usuario);
	}

	@Transactional
	public Usuario atualizar(Usuario usuario) {
		entityManager.detach(usuario);
		emailUniqueValidator.verificarSeEmailEValido(usuario);

		return repository.save(usuario);
	}

	@Transactional
	public void deletarPorId(Long id) {
		Usuario usuario = buscarPorId(id);

		try {
			repository.deleteById(id);
			repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(
					"Não foi possivel excluir o usuario '%s', pois ele estar em uso no sistema!", usuario.getNome()));
		}
	}

	@Transactional
	public void adicionarEndereco(Endereco endereco, Long usuarioId) {
		Usuario usuario = buscarPorId(usuarioId);

		usuario.adicionarEndereco(endereco);
	}

	@Transactional
	public void removerEndereco(Long usuarioId) {
		Usuario usuario = buscarPorId(usuarioId);

		usuario.removerEndereco();
	}

}
