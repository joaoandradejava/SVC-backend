package com.totalmed.svcbackend.core.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("jwt")
public class JwtConfigProperties {

	/**
	 * Tempo de expiração do token JWT em milisegundos
	 */
	private Long tempoDeExpiracaoDoTokenJwt;

	/**
	 * Senha para criptografar o token jwt
	 */
	private String senhaDeCriptografiaDoToken;

	public JwtConfigProperties() {
	}

	public Long getTempoDeExpiracaoDoTokenJwt() {
		return tempoDeExpiracaoDoTokenJwt;
	}

	public void setTempoDeExpiracaoDoTokenJwt(Long tempoDeExpiracaoDoTokenJwt) {
		this.tempoDeExpiracaoDoTokenJwt = tempoDeExpiracaoDoTokenJwt;
	}

	public String getSenhaDeCriptografiaDoToken() {
		return senhaDeCriptografiaDoToken;
	}

	public void setSenhaDeCriptografiaDoToken(String senhaDeCriptografiaDoToken) {
		this.senhaDeCriptografiaDoToken = senhaDeCriptografiaDoToken;
	}

}
