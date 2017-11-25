package org.moita.sbe.jms;
import javax.jms.JMSException;

import org.moita.sbe.jms.config.MessagingConfiguration;
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
public class MessageReceiver {
    static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);
 
    @Autowired
    OrderService orderService;
    
    @JmsListener(destination = MessagingConfiguration.ORDER_QUEUE)
    public void receiveOrder(final Message<Order> message) throws JMSException {
        MessageHeaders headers =  message.getHeaders();
        LOG.info("Application : headers received : {}", headers);
        Order response = message.getPayload();
        LOG.info("Application : response received : {}",response);
    }
}