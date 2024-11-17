package com.example.sistem_prijave_za_poso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.sistem_prijave_za_poso.Repositories")
public class SistemPrijaveZaPosoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemPrijaveZaPosoApplication.class, args);
	}

}
