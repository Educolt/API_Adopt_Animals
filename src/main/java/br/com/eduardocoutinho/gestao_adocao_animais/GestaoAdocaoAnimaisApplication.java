package br.com.eduardocoutinho.gestao_adocao_animais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Gestão de Adoção de Animais",
		description = "API responsavel pela gestão de um sistema de adoção de animais.",
		version = "1"
	)
)
public class GestaoAdocaoAnimaisApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoAdocaoAnimaisApplication.class, args);
	}

}
