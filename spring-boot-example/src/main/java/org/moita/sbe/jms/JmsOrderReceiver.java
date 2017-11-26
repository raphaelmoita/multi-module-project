package org.moita.sbe.jms;
import javax.jms.JMSException;

import org.moita.sbe.jms.config.P2PConfig;
import org.moita.sbe.jms.model.Order;
import org.moita.sbe.jms.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
 
@Component
public class JmsOrderReceiver {
    static final Logger LOG = LoggerFactory.getLogger(JmsOrderReceiver.class);
 
    @Autowired
    OrderService orderService;
    
    @JmsListener(destination = P2PConfig.QUEUE, containerFactory = "jmsListenerP2PContainerFactory")
    public void receiveOrder(final Message<Order> message) throws JMSException {
        MessageHeaders headers =  message.getHeaders();
        LOG.info("Application : headers received : {}", headers);
        Order response = message.getPayload();
        LOG.info("Application : response received : {}",response);
    }
}