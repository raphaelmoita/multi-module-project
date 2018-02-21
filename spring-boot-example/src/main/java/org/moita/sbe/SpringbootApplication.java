package org.moita.sbe;

import javax.annotation.PostConstruct;

import org.moita.sbe.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@SpringBootApplication
@PropertySource("classpath:messages.properties")
public class SpringbootApplication {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private Config config;
	
	@Autowired
	@Qualifier("profile-flow")
	String profile;
	
	@Value("${post-constructor-msg}")
	private String postContructorMessage;
    
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
	
	@PostConstruct
	private void execute() {
		System.out.println("From env: " + env.getProperty("post-constructor-msg"));
		System.out.println("From @Value: "+ postContructorMessage);
		System.out.println(config.getKey());
		System.out.println("Profile:" + profile);
    }
}
