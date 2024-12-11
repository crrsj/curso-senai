package br.com.livros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
			title = "API - Sistema para gerenciar livros.",
			version = "1.0",
			description = "Documentando uma API para um sistema para gerenciamento de livros.",
			contact = @Contact(name = "Carlos Roberto", email = "crrsj1@gmail.com")
		)
	)
public class ApiLivrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiLivrosApplication.class, args);
	}

}
