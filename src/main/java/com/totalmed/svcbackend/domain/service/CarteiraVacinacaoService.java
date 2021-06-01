package com.totalmed.svcbackend.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.totalmed.svcbackend.domain.exception.NegocioException;
import com.totalmed.svcbackend.domain.model.CarteiraVacinacao;
import com.totalmed.svcbackend.domain.model.Usuario;
import com.totalmed.svcbackend.domain.model.VacinaTomada;
import com.totalmed.svcbackend.domain.repository.CarteiraVacinacaoRepository;
import com.totalmed.svcbackend.domain.service.crud.CadastroUsuarioService;

@Service
public class CarteiraVacinacaoService {

	@Autowired
	private CarteiraVacinacaoRepository repository;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	public CarteiraVacinacao buscarCarteiraDoUsuario(Long usuarioId) {
		cadastroUsuarioService.buscarPorId(usuarioId);

		return repository.buscarCarteiraDoUsuario(usuarioId)
				.orElseThrow(() -> new NegocioException("Você não possui uma carteira de vacinação!"));
	}

	@Transactional
	public CarteiraVacinacao inserirCarteira(CarteiraVacinacao carteiraVacinacao, Long usuarioId) {
		Usuario usuario = cadastroUsuarioService.buscarPorId(usuarioId);

		if (usuario.getCarteiraVacinacao() != null) {
			throw new NegocioException("Você já possui uma carteira de vacinação");
		}

		carteiraVacinacao.setUsuario(usuario);
		carteiraVacinacao = repository.save(carteiraVacinacao);

		usuario.setCarteiraVacinacao(carteiraVacinacao);

		return carteiraVacinacao;
	}

	@Transactional
	public void removerCarteiraDoUsuario(Long usuarioId) {
		Usuario usuario = cadastroUsuarioService.buscarPorId(usuarioId);
		CarteiraVacinacao carteiraVacinacao = buscarCarteiraDoUsuario(usuarioId);

		usuario.setCarteiraVacinacao(null);
		repository.deleteById(carteiraVacinacao.getId());

	}

	@Transactional
	public void adicionarVacinaTomada(VacinaTomada vacinaTomada, Long usuarioId) {
		CarteiraVacinacao carteiraVacinacao = buscarCarteiraDoUsuario(usuarioId);
		vacinaTomada.setCarteiraVacinacao(carteiraVacinacao);

		carteiraVacinacao.adicionarVacinaTomada(vacinaTomada);
	}

}
