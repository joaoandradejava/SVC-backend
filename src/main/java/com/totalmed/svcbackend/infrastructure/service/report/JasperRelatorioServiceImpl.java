package com.totalmed.svcbackend.infrastructure.service.report;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.totalmed.svcbackend.domain.service.report.RelatorioService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class JasperRelatorioServiceImpl implements RelatorioService {

	@Override
	public String gerarRelatorio(String nomeDoRelatorio, Map<String, Object> parametros, List<?> lista)
			throws Exception {
		InputStream caminhoRelatorio = getClass().getResourceAsStream("/relatorios/" + nomeDoRelatorio + ".jasper");
		JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(lista);

		JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRelatorio, parametros,
				jrBeanCollectionDataSource);

		return "data:application/pdf;base64,"
				+ Base64.encodeBase64String(JasperExportManager.exportReportToPdf(jasperPrint));
	}

}
