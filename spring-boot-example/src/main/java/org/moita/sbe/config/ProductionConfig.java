package org.moita.sbe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("Prod")
@Configuration
public class ProductionConfig {
	
	@Bean
	public Config config() {
		return new Config.Builder().key("prodkey-111-222-333").build();
	}
}
