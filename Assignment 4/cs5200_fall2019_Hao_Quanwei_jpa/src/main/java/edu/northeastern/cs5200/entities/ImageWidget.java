package edu.northeastern.cs5200.entities;

import javax.persistence.Entity;

@Entity
public class ImageWidget extends Widget {
	private String src;
	
	public ImageWidget() {
		super();
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}
	
}
