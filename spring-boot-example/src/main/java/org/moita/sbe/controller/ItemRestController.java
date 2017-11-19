package org.moita.sbe.controller;

import java.util.Collection;

import org.moita.sbe.model.Item;
import org.moita.sbe.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/item")
public class ItemRestController {
	
	@Autowired
	Service service;

	@GetMapping("/list")
    public ResponseEntity<Collection<Item>> list() {
		Collection<Item> list = service.list(); 
		if(list == null || list.isEmpty()){
            return new ResponseEntity<Collection<Item>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
		return new ResponseEntity<Collection<Item>>(list, HttpStatus.OK);
    }	
	
	@GetMapping("/get")
    public ResponseEntity<Item> get(@PathVariable Long id) {
    	Item item = service.get(id);
    	if(item == null){
            return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
        }
    	return new ResponseEntity<Item>(item, HttpStatus.OK);
    }
	
	@PostMapping(value = "/add")
    public ResponseEntity<Item> add(@RequestBody Item item) {
    	service.add(item);
    	return new ResponseEntity<Item>(item, HttpStatus.OK);
    }
	
	@PutMapping("/update")
    public ResponseEntity<Item> update(@RequestBody Item item) {
    	service.update(item);
    	return new ResponseEntity<Item>(item, HttpStatus.OK);
    }
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Long> deleteCustomer(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
}
