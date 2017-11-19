package org.moita.sbe.persistence;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.moita.sbe.model.Item;
import org.springframework.stereotype.Controller;

@Controller
public class ItemDAO {
	
	private ConcurrentHashMap<Long, Item> db = new ConcurrentHashMap<>();
	
	public Collection<Item> list() {
		return db.values();
	}
	
	public Item get(Long id) {
		return db.get(id);
	}
	
	public void delete(Long id) {
		db.remove(id);
	}
	
	public void add(Item item) {
		db.put(item.getId(), item);
	}
	
	public void update(Item item) {
		db.put(item.getId(), item);
	}

}
