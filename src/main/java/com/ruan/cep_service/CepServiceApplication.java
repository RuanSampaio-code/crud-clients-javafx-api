package com.ruan.cep_service;

import com.ruan.cep_service.configSpringJavaFx.SpringContext;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CepServiceApplication {

	private static ConfigurableApplicationContext springContext;

	public static void main(String[] args) {
		// Inicia a aplicação Spring Boot
		springContext = SpringApplication.run(CepServiceApplication.class, args);

		// Seta o contexto do Spring para ser usado no JavaFX
		SpringContext.setApplicationContext(springContext);

		// Inicia a aplicação JavaFX
		Application.launch(JavaFXApp.class, args);


	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
