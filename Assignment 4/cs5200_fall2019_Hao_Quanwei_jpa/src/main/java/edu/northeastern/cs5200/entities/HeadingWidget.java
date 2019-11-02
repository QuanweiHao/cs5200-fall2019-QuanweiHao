package edu.northeastern.cs5200.entities;

import javax.persistence.Entity;

@Entity
public class HeadingWidget extends Widget {
	private int size;
	
	public HeadingWidget() {
		super();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
