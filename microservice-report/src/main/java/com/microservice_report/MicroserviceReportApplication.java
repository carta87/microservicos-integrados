package com.microservice_report;

import net.sf.jasperreports.engine.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class MicroserviceReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceReportApplication.class, args);
	}

	@Bean
	CommandLineRunner init(){
		return args -> {
			String destinationPath =
					"microservice-report" + File.separator +
					"src" + File.separator +
							"main"+ File.separator +
							"resources"+ File.separator +
							"static" + File.separator +
							"ReportGenrates.pdf";

			String filePath =
					"microservice-report" + File.separator +
					"src" + File.separator +
							"main"+ File.separator +
							"resources"+ File.separator +
							"templates" + File.separator +
							"report" + File.separator +
							"comprobantePago.jrxml";

			LocalDateTime date = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

			Map<String, Object> parameters = new HashMap<>();
			parameters.put("numCombante", "00001245");
			parameters.put("fechaComprobante", formatter.format(date));
			parameters.put("valorPagado",new BigDecimal(30000));
			parameters.put("medioPago", "Efectivo");
			parameters.put("nomAlumno", "Juliana Arenas");
			parameters.put("nomAcudiente", "Gustavo Angarita");
			parameters.put("imageDir", "classpath:/static/images/");

			JasperReport report = JasperCompileManager.compileReport(filePath);
			JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

			JasperExportManager.exportReportToPdfFile(print, destinationPath);
			System.out.println("Creacion de reporte exitoso");

		};
	}

}
