package org.moita.sbe;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"org.moita.sbe"})
public class SpringbootApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
	
	@PostConstruct
	private void execute() {
		System.out.println("SpringbootApplication post constructor!");
    }
}
