package com.moita.sbe.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.moita.sbe.model.Item;
import org.moita.sbe.persistence.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ItemDAO.class })
public class ItemDAOTest {
	
	@Autowired
	private ItemDAO dao;
	
	@Test
	public void exampleTest() {
		Item item = new Item(123, "One Two Three");	
		
		dao.add(item);
		assertThat(dao.get(item.getId())).isEqualTo(item);
		
		dao.delete(item.getId());
		assertThat(dao.get(item.getId())).isNull();
		
		dao.add(new Item(1L, "One"));
		dao.add(new Item(2L, "Two"));
		assertThat(dao.list().size()).isEqualTo(2);
	}

}
