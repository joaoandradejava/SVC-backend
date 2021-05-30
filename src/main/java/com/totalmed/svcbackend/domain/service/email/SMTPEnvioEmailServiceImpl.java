package com.totalmed.svcbackend.domain.service.email;

import javax.mail.internet.MimeMessage;

import com.totalmed.svcbackend.domain.model.Usuario;

public class SMTPEnvioEmailServiceImpl extends AbstractEnvioEmail {

	@Override
	public void enviarEmail(Usuario usuario, String titulo, CorpoMensagem corpoMensagem) {
		MimeMessage mimeMessage = prepararMimeMessage(usuario, titulo, corpoMensagem);
		javaMailSender.send(mimeMessage);

	}

}
