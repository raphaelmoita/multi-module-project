package org.moita.sbe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("Dev")
@Configuration 
public class DevelopmentConfig {
	
	@Bean
	public Config config() {
		return new Config.Builder().key("devkey-111-222-333").build();
	}

}
