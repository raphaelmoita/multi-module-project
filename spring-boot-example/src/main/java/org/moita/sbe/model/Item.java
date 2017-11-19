package org.moita.sbe.model;

import java.util.Objects;

public class Item {
	
	private long id;
	private String description;
	
	public Item() {
		// TODO Auto-generated constructor stub
	}
		
	public Item(long id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
		Item that = (Item) obj;
		return Objects.equals(description, that.description) 
			   && Objects.equals(id, that.id);
	}
	

}
