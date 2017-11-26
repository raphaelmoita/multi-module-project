package org.moita.sbe.jms.service;

import java.util.UUID;

import org.moita.sbe.jms.JmsOrderSender;
import org.moita.sbe.jms.model.Order;
import org.moita.sbe.jms.model.OrderStatus;
import org.moita.sbe.jms.topic.JmsOrderPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	JmsOrderSender orderSender;
	
	@Autowired
	JmsOrderPublisher orderPublisher;

	@Override
	public void send(Order order) {
		order.setOrderId(UUID.randomUUID().toString());
		order.setStatus(OrderStatus.CREATED);
		LOG.info("Application : sending order request {}", order);
		orderSender.send(order);
	}
	
	@Override
	public void publish(Order order) {
		order.setOrderId(UUID.randomUUID().toString());
		order.setStatus(OrderStatus.PENDING);
		LOG.info("Application : publishing order {}", order);
		orderPublisher.publish(order);
	}
	
}