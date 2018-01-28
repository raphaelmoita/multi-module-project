package org.moita.sbe.service;

import java.io.IOException;
import java.util.Collection;

import org.moita.sbe.model.Item;

public interface Service {
	
	void add(Item item) throws IOException;
	
	Collection<Item> list();
	
	void delete(Long id);
	
	void update(Item item);

	Item get(Long id);

}
