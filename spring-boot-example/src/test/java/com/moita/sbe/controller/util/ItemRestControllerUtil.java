package com.moita.sbe.controller.util;

import java.net.URI;

import org.moita.sbe.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

public class ItemRestControllerUtil {
	
	@Value("http://localhost:${local.server.port}")
	private String url;

	@Autowired
	private TestRestTemplate restTemplate;
	
	public ResponseEntity<Item> post(Item item, String endPoint) {
		RequestEntity<Item> request = RequestEntity
			     .post(URI.create(endPoint))
			     .accept(MediaType.APPLICATION_JSON)
			     .body(item);
		return this.restTemplate.exchange(request, Item.class);
	}
	
	public ResponseEntity<Item> get(Long id, String endPoint) {
		RequestEntity<Void> request = RequestEntity
			     .get(URI.create(endPoint + id))
			     .accept(MediaType.APPLICATION_JSON)
			     .build();
		
		return this.restTemplate.exchange(request, Item.class);
	}
			
	public ResponseEntity<Item> put(Item item, String endPoint) {
		RequestEntity<Item> request = RequestEntity
			     .put(URI.create(endPoint))
			     .accept(MediaType.APPLICATION_JSON)
			     .body(new Item(item.getId(), item.getDescription()));
		
		return this.restTemplate.exchange(request, Item.class);
	}
	
	public ResponseEntity<Long> delete(long id) {
		RequestEntity<Void> request = RequestEntity
			     .delete(URI.create("/rest/item/delete/" + id))
			     .accept(MediaType.APPLICATION_JSON)
			     .build();
		
		return this.restTemplate.exchange(request, Long.class);
	}
}
