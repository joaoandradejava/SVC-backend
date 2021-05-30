package com.totalmed.svcbackend.core.openapiconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().info(new Info().title("SVC(Smart Vaccination Card) API").description(
				"A empresa TotalMed tem como o objetivo criar um app parecido com uma carteira de vacinação virtual, onde o usuário  diga o dia e o local onde foi aplicada a vacina, o histórico médico do paciente e o local onde ele reside. Criar um mecanismo onde envie notificações para o usuário informando se o paciente precisa reforçar a dose, informar o local mais próximo e agendar um dia para que ele vá até o local.")
				.contact(new Contact().email("totalmedsvc@gmail.com").name("email de contato da total med"))
				.version("V1.0"));
	}
}
