package org.moita.sbe.jms.config;
import javax.jms.ConnectionFactory;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
 
@Configuration
@EnableJms
public class MessagingListnerConfiguration {
 
    @Autowired
    ConnectionFactory connectionFactory;
     
    private DefaultJmsListenerContainerFactory containerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("1-1");
        return factory;
    }
    
    @Bean
    @Qualifier("jmsListenerP2PContainerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerP2PContainerFactory() {
        return containerFactory();
    }
    
    @Bean
    @Qualifier("jmsListenerTopicContainerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerTopicContainerFactory() {
    	DefaultJmsListenerContainerFactory containerFactory = containerFactory();
    	containerFactory.setPubSubDomain(true);
        return containerFactory;
    }
}