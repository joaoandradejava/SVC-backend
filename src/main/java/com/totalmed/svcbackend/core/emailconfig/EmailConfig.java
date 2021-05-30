package com.totalmed.svcbackend.core.emailconfig;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.totalmed.svcbackend.domain.service.email.EnvioEmailService;
import com.totalmed.svcbackend.domain.service.email.SMTPEnvioEmailServiceImpl;
import com.totalmed.svcbackend.domain.service.email.SandboxEnvioEmailService;

@Configuration
public class EmailConfig {

	@Autowired
	private EmailConfigProperties emailConfigProperties;

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(emailConfigProperties.getHost());
		mailSender.setPort(emailConfigProperties.getPorta());

		mailSender.setUsername(emailConfigProperties.getEmail());
		mailSender.setPassword(emailConfigProperties.getSenha());

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

	@Bean
	public EnvioEmailService envioEmailService() {
		if (emailConfigProperties.getTipoEnvioEmail() == TipoEnvioEmail.SMTP
				|| emailConfigProperties.getTipoEnvioEmail() == null) {
			return new SMTPEnvioEmailServiceImpl();
		}

		return new SandboxEnvioEmailService();
	}

}
