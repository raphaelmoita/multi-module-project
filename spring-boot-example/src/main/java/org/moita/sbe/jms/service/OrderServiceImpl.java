package org.moita.sbe.jms.service;

import java.util.UUID;

import org.moita.sbe.jms.MessageSender;
import org.moita.sbe.jms.model.Order;
import org.moita.sbe.jms.model.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	MessageSender messageSender;

	@Override
	public void sendOrder(Order order) {
		order.setOrderId(UUID.randomUUID().toString());
		order.setStatus(OrderStatus.CREATED);
		LOG.info("Application : sending order request {}", order);
		messageSender.sendMessage(order);
	}
}