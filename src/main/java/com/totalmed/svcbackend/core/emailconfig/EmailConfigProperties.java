package com.totalmed.svcbackend.core.emailconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("email")
public class EmailConfigProperties {

	/**
	 * Host do gmail
	 */
	private String host;

	/**
	 * Porta do gmail
	 */
	private int porta;

	/**
	 * Email
	 */
	private String email;

	/**
	 * Senha
	 */
	private String senha;

	/**
	 * caixinha sandbox para envio de email em desenvolvimento
	 */
	private String emailSandbox;

	private TipoEnvioEmail tipoEnvioEmail;

	public EmailConfigProperties() {
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmailSandbox() {
		return emailSandbox;
	}

	public void setEmailSandbox(String emailSandbox) {
		this.emailSandbox = emailSandbox;
	}

	public TipoEnvioEmail getTipoEnvioEmail() {
		return tipoEnvioEmail;
	}

	public void setTipoEnvioEmail(TipoEnvioEmail tipoEnvioEmail) {
		this.tipoEnvioEmail = tipoEnvioEmail;
	}

}
