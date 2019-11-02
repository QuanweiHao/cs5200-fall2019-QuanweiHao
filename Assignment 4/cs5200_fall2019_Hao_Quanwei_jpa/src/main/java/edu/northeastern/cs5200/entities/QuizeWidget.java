package edu.northeastern.cs5200.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class QuizeWidget extends Widget {
	@OneToMany(mappedBy = "quizeWidget")
	private List<Question> questions;

	public QuizeWidget() {
		super();
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
}
