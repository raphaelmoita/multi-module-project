package org.moita.sbe.jms.service;

import org.moita.sbe.jms.model.Order;

public interface OrderService {
	
	public void send(Order order);
	
	public void publish(Order order);

}