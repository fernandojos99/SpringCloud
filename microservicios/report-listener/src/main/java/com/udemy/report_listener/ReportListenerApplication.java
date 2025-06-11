package com.udemy.report_listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ReportListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportListenerApplication.class, args);
		   log.info("🚀 Aplicación iniciada correctamente");
	}

}
