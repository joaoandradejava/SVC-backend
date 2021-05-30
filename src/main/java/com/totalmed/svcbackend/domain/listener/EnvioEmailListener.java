package com.totalmed.svcbackend.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.totalmed.svcbackend.domain.model.Usuario;
import com.totalmed.svcbackend.domain.observer.UsuarioCadastradoObserver;
import com.totalmed.svcbackend.domain.observer.UsuarioEsqueceuSenhaObserver;
import com.totalmed.svcbackend.domain.service.email.CorpoMensagem;
import com.totalmed.svcbackend.domain.service.email.EnvioEmailService;

@Component
public class EnvioEmailListener {

	@Autowired
	private EnvioEmailService envioEmailService;

	@EventListener
	public void usuarioCadastradoListener(UsuarioCadastradoObserver usuarioCadastradoObserver) {
		Usuario usuario = usuarioCadastradoObserver.getUsuarioCadastrado();
		envioEmailService.enviarEmail(usuario, "Cadastrado realizado com sucesso!",
				new CorpoMensagem("usuario-cadastrado.html", usuario));
	}

	@EventListener
	public void usuarioEsqueceuSenhaListener(UsuarioEsqueceuSenhaObserver usuarioEsqueceuSenhaObserver) {
		envioEmailService.enviarEmail(usuarioEsqueceuSenhaObserver.getUsuario(), "Nova senha",
				new CorpoMensagem("usuario-esqueceu-senha.html", usuarioEsqueceuSenhaObserver));
	}
}
