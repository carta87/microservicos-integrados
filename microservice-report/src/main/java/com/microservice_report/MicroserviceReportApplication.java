package com.microservice_report;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import static java.lang.System.*;

@EnableDiscoveryClient
@SpringBootApplication
@EntityScan(basePackages = {"com.library.entidades.jpa.entity"})
public class MicroserviceReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceReportApplication.class, args);
	}

	@Bean
	CommandLineRunner init(){
		return args -> {
			String prueba = "<script>alert('hola mundo')</script>$final";

			//prueba = String.valueOf(prueba.contains("<"));

			String caracterInvalid = "<";

			if(prueba.contains(caracterInvalid)){
                out.println("El texto contiene etiquetas HTML");
            } else {
                out.println("El texto no contiene etiquetas HTML");
            }

			// Sanitizar el texto para evitar ataques XSS
			PolicyFactory policyFactory = Sanitizers.FORMATTING;
			String sanitizedText = policyFactory.sanitize(prueba);
			out.println("salida"+sanitizedText);

		};
	}

}
