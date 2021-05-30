package com.totalmed.svcbackend.domain.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.totalmed.svcbackend.domain.exception.NegocioException;
import com.totalmed.svcbackend.domain.model.Usuario;
import com.totalmed.svcbackend.domain.service.crud.CadastroUsuarioService;

@Service
public class UsuarioService {

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Transactional
	public void ativar(Long id) {
		Usuario usuario = cadastroUsuarioService.buscarPorId(id);

		usuario.ativar();
	}

	@Transactional
	public void desativar(Long id) {
		Usuario usuario = cadastroUsuarioService.buscarPorId(id);

		usuario.desativar();
	}

	@Transactional
	public void alterarSenha(String senhaAtual, String novaSenha, Long id) {
		Usuario usuario = cadastroUsuarioService.buscarPorId(id);
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		if (!bCryptPasswordEncoder.matches(senhaAtual, usuario.getSenha())) {
			throw new NegocioException("A senha atual informada está incorreta!");
		}

		usuario.setSenha(bCryptPasswordEncoder.encode(novaSenha));
	}

	@Transactional
	public String esqueciASenha(String email) {
		Usuario usuario = cadastroUsuarioService.buscarPorEmail(email);
		Random random = new Random();
		String novaSenha = "totalmedSVC";
		char[] caracteres = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'L', 'M', 'N', 'O', 'a', 'b', 'c', 'd',
				'e', 'f', 'g', '1', '2', '3', '4', '5' };

		for (int i = 0; i < caracteres.length; i++) {
			novaSenha += caracteres[random.nextInt(caracteres.length)];
		}

		usuario.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
		return novaSenha;
	}
}
