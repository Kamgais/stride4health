package com.thb.mux.stride4health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Stride4HealthApplication {

	public static void main(String[] args) {
		SpringApplication.run(Stride4HealthApplication.class, args);
	}

}
