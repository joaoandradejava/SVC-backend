package com.totalmed.svcbackend.domain.service.email;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.totalmed.svcbackend.core.emailconfig.EmailConfigProperties;
import com.totalmed.svcbackend.domain.exception.EnvioEmailException;
import com.totalmed.svcbackend.domain.model.Usuario;

import freemarker.template.Configuration;
import freemarker.template.Template;

public abstract class AbstractEnvioEmail implements EnvioEmailService {

	@Autowired
	protected JavaMailSender javaMailSender;

	@Autowired
	private EmailConfigProperties emailConfigProperties;

	@Autowired
	private Configuration configuration;

	protected MimeMessage prepararMimeMessage(Usuario usuario, String titulo, CorpoMensagem corpoMensagem) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
			mimeMessageHelper.setSentDate(new Date());
			mimeMessageHelper.setFrom(emailConfigProperties.getEmail());
			mimeMessageHelper.setTo(usuario.getEmail());
			mimeMessageHelper.setSubject(titulo);
			mimeMessageHelper.setText(processarETransformarOTemplateHtmlEmString(corpoMensagem), true);

			return mimeMessage;
		} catch (MessagingException e) {
			throw new EnvioEmailException(
					"Ocorreu um erro inesperado ao tentar preparar o MimeMessage para o Envio de email");
		}
	}

	private String processarETransformarOTemplateHtmlEmString(CorpoMensagem corpoMensagem) {
		try {
			Template template = configuration.getTemplate(corpoMensagem.getNomeHtmlMail(),
					LocaleContextHolder.getLocale(), "UTF-8");

			return FreeMarkerTemplateUtils.processTemplateIntoString(template, corpoMensagem.getObjeto());
		} catch (Exception e) {
			throw new EnvioEmailException(
					"Ocorreu um erro inesperado ao tentar processar o template Html para transformar em String. Para que assim se possa ter um corpo de mensagem do envio de email");
		}
	}
}
