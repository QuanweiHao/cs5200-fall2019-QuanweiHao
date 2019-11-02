package edu.northeastern.cs5200.entities;

import javax.persistence.Entity;

@Entity
public class ParagraphWidget extends Widget {
	private String text;

	public ParagraphWidget() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
