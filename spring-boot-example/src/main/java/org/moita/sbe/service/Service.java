package org.moita.sbe.service;

import java.util.Collection;

import org.moita.sbe.model.Item;

public interface Service {
	
	void add(Item item);
	
	Collection<Item> list();
	
	void delete(Long id);
	
	void update(Item item);

	Item get(Long id);

}
