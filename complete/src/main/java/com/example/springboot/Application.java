package com.example.springboot;

import java.util.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// this runs the main application
		SpringApplication.run(Application.class, args);

		// Add a user to the userstore for testing
		ArrayList<String> newUser = new ArrayList<String>();
		newUser.add("testuser2@email.com");
		newUser.add("password");
		TvlRepository.userstore.add(newUser);

		// Add a fine to the finestore for testing
		ArrayList<String> newFine = new ArrayList<String>();
		newFine.add("testuser2@email.com");
		newFine.add("Chichester Street,  BT1 4JQ"); // Location
		newFine.add("09/05/2022"); // Contravention date
		newFine.add("12:39 To 12:47"); // Contravention time
		newFine.add("01"); // Contravention code
		newFine.add("FG245"); // Issued by the Civil Enforcement Officer
		newFine.add("Parked in a restricted street during prescribed hours"); // Parking contravention
		TvlRepository.finestore.add(newFine);

	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

}
