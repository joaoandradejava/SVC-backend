package com.totalmed.svcbackend.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.totalmed.svcbackend.domain.exception.CarteiraVacinacaoNaoEncontradaException;
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

	public CarteiraVacinacao buscarCarteiraDoUsuario(Long usuarioId, Long carteiraId) {
		cadastroUsuarioService.buscarPorId(usuarioId);
		buscarPorId(carteiraId);

		return repository.buscarCarteiraDoUsuario(usuarioId)
				.orElseThrow(() -> new NegocioException(
						String.format("A carteira de vacinaçãao de id %d não estar associada com o usuario de id %d",
								carteiraId, usuarioId)));
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
	public void removerCarteiraDoUsuario(Long usuarioId, Long carteiraId) {
		buscarCarteiraDoUsuario(usuarioId, carteiraId);
		Usuario usuario = cadastroUsuarioService.buscarPorId(usuarioId);

		usuario.setCarteiraVacinacao(null);
		repository.deleteById(carteiraId);

	}

	private CarteiraVacinacao buscarPorId(Long carteiraId) {
		return repository.findById(carteiraId)
				.orElseThrow(() -> new CarteiraVacinacaoNaoEncontradaException(carteiraId));
	}

	@Transactional
	public void adicionarVacinaTomada(VacinaTomada vacinaTomada, Long usuarioId, Long carteiraId) {
		CarteiraVacinacao carteiraVacinacao = buscarCarteiraDoUsuario(usuarioId, carteiraId);

		vacinaTomada.setCarteiraVacinacao(carteiraVacinacao);

		carteiraVacinacao.adicionarVacinaTomada(vacinaTomada);
	}

}
