package com.totalmed.svcbackend.core.jwt;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.totalmed.svcbackend.api.exceptionhandler.Error;
import com.totalmed.svcbackend.core.security.UsuarioAutenticado;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private JwtUtil jwtUtil;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		setAuthenticationManager(authenticationManager);
		setAuthenticationFailureHandler(new FailureHandler());

		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			LoginDTO loginDTO = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);

			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					loginDTO.getCpf(), loginDTO.getSenha());

			return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado) authResult.getPrincipal();

		String tokenJwt = "Bearer " + jwtUtil.gerarTokenJwt(usuarioAutenticado.getUsername());
		response.setHeader("Authorization", tokenJwt);
		response.getWriter().write(json(tokenJwt, usuarioAutenticado));
		response.setContentType("application/json");

	}

	private String json(String token, UsuarioAutenticado usuarioAutenticado) {
		StringBuilder builder = new StringBuilder();
		builder.append("{\n");
		builder.append("\"id\": " + usuarioAutenticado.getId() + ",\n");
		builder.append("\"token\": \"" + token + "\",\n");
		builder.append("\"isAdmin\": " + usuarioAutenticado.isAdmin() + "\n");
		builder.append("}");

		return builder.toString();
	}

	private class FailureHandler implements AuthenticationFailureHandler {

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {
			HttpStatus status = HttpStatus.UNAUTHORIZED;
			response.setStatus(status.value());
			response.getWriter().write(json(status));
			response.setContentType("application/json");

		}

		private String json(HttpStatus status) {
			StringBuilder builder = new StringBuilder();
			Error error = Error.ERRO_NA_AUTENTICACAO;
			String message = "Cpf incorreto ou senha incorreta.";

			builder.append("{\n");
			builder.append("\"timestamp\": \"" + LocalDateTime.now() + "\",\n");
			builder.append("\"type\": \"" + error.getType() + "\",\n");
			builder.append("\"title\": \"" + error.getTitle() + "\",\n");
			builder.append("\"status\": \"" + status.value() + "\",\n");
			builder.append("\"detail\": \"" + message + "\",\n");
			builder.append("\"userMessage\": \"" + message + "\"\n");
			builder.append("}");

			return builder.toString();
		}

	}

}
