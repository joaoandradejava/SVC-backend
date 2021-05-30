package com.totalmed.svcbackend.domain.service.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.totalmed.svcbackend.core.emailconfig.EmailConfigProperties;
import com.totalmed.svcbackend.domain.exception.EnvioEmailException;
import com.totalmed.svcbackend.domain.model.Usuario;

public class SandboxEnvioEmailService extends AbstractEnvioEmail {

	@Autowired
	private EmailConfigProperties emailConfigProperties;

	@Override
	public void enviarEmail(Usuario usuario, String titulo, CorpoMensagem corpoMensagem) {
		try {
			MimeMessage mimeMessage = prepararMimeMessage(usuario, titulo, corpoMensagem);
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
			mimeMessageHelper.setTo(emailConfigProperties.getEmailSandbox());
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new EnvioEmailException(
					"Ocorreu um erro inesperado ao tentar preparar o MimeMessage para o Envio de email");
		}
	}

}
