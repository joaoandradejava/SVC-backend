package com.totalmed.svcbackend.domain.service.report;

import java.util.List;
import java.util.Map;

public interface RelatorioService {

	public String gerarRelatorio(String nomeDoRelatorio, Map<String, Object> parametros, List<?> lista)
			throws Exception;
}
