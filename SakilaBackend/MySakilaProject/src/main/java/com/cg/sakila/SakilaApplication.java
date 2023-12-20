package com.cg.sakila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.cg.sakila.repositories")
public class SakilaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SakilaApplication.class, args);
		
	}

}

