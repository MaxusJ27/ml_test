package com.moneylion.ta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.moneylion.ta.service.SeedingService;

@SpringBootApplication
public class TaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaApplication.class, args);
	}
	// seeding data, run only once or locally
	// @Bean
    // public CommandLineRunner seedData(SeedingService seedingService) {
	// 	return args -> seedingService.seedData();
    // }

}
