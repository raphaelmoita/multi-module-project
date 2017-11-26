package org.moita.sbe.jms.topic;
import org.moita.sbe.jms.config.TopicConfig;
import org.moita.sbe.jms.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
 
@Component
public class JmsOrderPublisher {
	
	@Autowired
	@Qualifier("jmsTopicTemplate")
	JmsTemplate jmsTemplate;
	
	public void publish(final Order order){
		jmsTemplate.convertAndSend(TopicConfig.TOPIC, order);
	}
}