package org.moita.sbe.service;

import java.util.Collection;

import org.moita.sbe.model.Item;
import org.moita.sbe.persistence.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemService implements Service {
	
	@Autowired
	private ItemDAO dao;
	
	@Override
	public Item get(Long id) {
		return dao.get(id);
	}
	
	@Override
	public void add(Item item) {
		dao.add(item);
		System.out.println("Added!!!");
	}
	
	@Override
	public Collection<Item> list() {
		return dao.list();
	}
	
	@Override
	public void delete(Long id) {
		dao.delete(id);
		System.out.println("Deleted!!!");
	}
	
	@Override
	public void update(Item item) {
		dao.update(item);
	}
}
