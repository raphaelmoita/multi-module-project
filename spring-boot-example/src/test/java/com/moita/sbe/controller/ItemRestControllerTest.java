package com.moita.sbe.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.moita.sbe.SpringbootApplication;
import org.moita.sbe.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { SpringbootApplication.class })
public class ItemRestControllerTest {

	@Value("http://localhost:${local.server.port}")
	private String url;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void exampleTest() {
		Item item = new Item(123, "One Two Three");

		post(item);
		
		get(123L);
		
		getReturningNoContent(321L);
		
		update(item);
		
		listReturningOneItem();
		
		delete(123L);
		
		listReturningNoContent();
	}
	
	private void post(Item item) {
		RequestEntity<Item> request = RequestEntity
			     .post(URI.create("/rest/item/add"))
			     .accept(MediaType.APPLICATION_JSON)
			     .body(item);
		
		ResponseEntity<Item> response = this.restTemplate.exchange(request, Item.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	private void get(Long id) {
		RequestEntity<Void> request = RequestEntity
			     .get(URI.create("/rest/item/get/" + id))
			     .accept(MediaType.APPLICATION_JSON)
			     .build();
		
		ResponseEntity<Item> response = this.restTemplate.exchange(request, Item.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getId()).isEqualTo(id);
	}
	
	private void getReturningNoContent(Long id) {
		RequestEntity<Void> request = RequestEntity
			     .get(URI.create("/rest/item/get/" + id))
			     .accept(MediaType.APPLICATION_JSON)
			     .build();
		
		ResponseEntity<Item> response = this.restTemplate.exchange(request, Item.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}
	
	private void update(Item item) {
		final String updateDescrition = "Updated description";
		RequestEntity<Item> request = RequestEntity
			     .put(URI.create("/rest/item/update/"))
			     .accept(MediaType.APPLICATION_JSON)
			     .body(new Item(item.getId(), updateDescrition));
		
		ResponseEntity<Item> putResponse = this.restTemplate.exchange(request, Item.class);
		assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(putResponse.getBody().getDescription()).isEqualTo(updateDescrition);
	}
	
	private void listReturningOneItem() {
		RequestEntity<Void> request = RequestEntity
			     .get(URI.create("/rest/item/list/"))
			     .accept(MediaType.APPLICATION_JSON)
			     .build();
		
		// return 209. Why?
		ResponseEntity<List<Item>> listResponse = this.restTemplate.exchange(request, new ParameterizedTypeReference<List<Item>>(){});
		assertThat(listResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(listResponse.getBody().size()).isEqualTo(1);
	}
	
	private void listReturningNoContent() {
		RequestEntity<Void> request = RequestEntity
			     .get(URI.create("/rest/item/list/"))
			     .accept(MediaType.APPLICATION_JSON)
			     .build();
		
		ResponseEntity<List<Item>> listResponse = this.restTemplate.exchange(request, new ParameterizedTypeReference<List<Item>>(){});
		assertThat(listResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}
	
	private void delete(long id) {
		RequestEntity<Void> request = RequestEntity
			     .delete(URI.create("/rest/item/delete/" + id))
			     .accept(MediaType.APPLICATION_JSON)
			     .build();
		
		ResponseEntity<Long> deleteResponse = this.restTemplate.exchange(request, Long.class);
		assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(deleteResponse.getBody()).isEqualTo(id);
	}
}
