package de.planerio.developertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("de.planerio.developertest")
@SpringBootApplication
@ComponentScan(basePackages = { "de.planerio.developertest", "de.planerio.developertest.controller" , "de.planerio.developertest.config"})
public class DeveloperTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeveloperTestApplication.class, args);
	}

}
