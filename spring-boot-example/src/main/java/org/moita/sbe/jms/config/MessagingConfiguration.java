package org.moita.sbe.jms.config;

import java.util.Arrays;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class MessagingConfiguration {
	
	public static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
	public static final String TRUSTED_PACKAGES = "org.moita.sbe";

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
		connectionFactory.setTrustedPackages(Arrays.asList(TRUSTED_PACKAGES));
		return connectionFactory;
	}
	
	@Bean
	@Qualifier("jmsP2PTemplate")
	public JmsTemplate jmsP2PTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName(P2PConfig.QUEUE);
		template.setPubSubDomain(false);
		return template;
	}
	
	@Bean
	@Qualifier("jmsTopicTemplate")
	public JmsTemplate jmsTopicTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName(TopicConfig.TOPIC);
		template.setPubSubDomain(true);
		return template;
	}

}