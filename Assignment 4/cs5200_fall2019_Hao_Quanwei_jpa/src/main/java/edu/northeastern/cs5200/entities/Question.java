package edu.northeastern.cs5200.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String question;
	private int points;
	@OneToMany
	private List<Answer> answers;
	@ManyToOne
	@JsonIgnore
	private QuizeWidget quizeWidget;
	
	public Question() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public QuizeWidget getQuizeWidget() {
		return quizeWidget;
	}

	public void setQuizeWidget(QuizeWidget quizeWidget) {
		this.quizeWidget = quizeWidget;
		if(!quizeWidget.getQuestions().contains(this)) {
			quizeWidget.getQuestions().add(this);
		}
	}
	

}
