package com.budget.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.bind.annotation.GetMapping;



@SpringBootApplication
@RestController
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@GetMapping("/")
	public String message() {
		return "<h2>Welcome to Budget Tracker Server Application</h2>";
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // allow all endpoints
                    .allowedOrigins("http://localhost:5173") // your frontend
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // allowed methods
                    .allowedHeaders("*"); // allow all headers
			}
		};
	}
}
