package org.moita.sbe.jms.topic;
import org.moita.sbe.jms.config.TopicConfig;
import org.moita.sbe.jms.model.Order;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
 
 
@Component
public class JmsOrderSubcriber2 {
	
	@JmsListener(destination = TopicConfig.TOPIC, containerFactory = "jmsListenerTopicContainerFactory")
	public void receive1(Order order){
		System.out.println("JmsOrderSubcriber2: " + order);
	}
}