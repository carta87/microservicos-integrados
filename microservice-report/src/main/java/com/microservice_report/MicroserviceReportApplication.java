package com.microservice_report;

import net.sf.jasperreports.engine.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@EnableDiscoveryClient
@SpringBootApplication
@EntityScan(basePackages = {"com.library.entidades.jpa.entity"})
public class MicroserviceReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceReportApplication.class, args);
	}

	/*@Bean
	CommandLineRunner init(){
		return args -> {

		};
	}*/

}
