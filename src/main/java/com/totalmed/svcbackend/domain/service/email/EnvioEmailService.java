package com.totalmed.svcbackend.domain.service.email;

import com.totalmed.svcbackend.domain.model.Usuario;

public interface EnvioEmailService {

	public void enviarEmail(Usuario usuario, String titulo, CorpoMensagem corpoMensagem);
}
