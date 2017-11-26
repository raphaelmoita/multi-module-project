package org.moita.sbe.controller;

import org.moita.sbe.jms.model.Order;
import org.moita.sbe.jms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/order")
public class OrderRestController {

	@Autowired
	OrderService orderService;

	@PostMapping("/new")
	public ResponseEntity<Order> sendOrderToQueue(@RequestBody Order order) {
		orderService.send(order);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@PostMapping("/pub")
	public ResponseEntity<Order> publishOrder(@RequestBody Order order) {
		orderService.publish(order);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
}
