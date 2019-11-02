package edu.northeastern.cs5200.entities;

import javax.persistence.Entity;

@Entity
public class ListWidget extends Widget {
	private String items;
	private Boolean ordered;
	public ListWidget() {
		super();
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public Boolean getOrdered() {
		return ordered;
	}
	public void setOrdered(Boolean ordered) {
		this.ordered = ordered;
	}
	
}
