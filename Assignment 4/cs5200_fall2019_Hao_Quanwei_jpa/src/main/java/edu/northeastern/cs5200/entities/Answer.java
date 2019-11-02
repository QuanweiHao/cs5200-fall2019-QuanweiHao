package edu.northeastern.cs5200.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
	private Student student;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "QUESTION_ID", referencedColumnName = "ID")
	private Question question;
	
	private Boolean trueFalseAnswer;
	private int multipleChoiceAnswer;
	
	public Answer() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
		if(!student.getAnswers().contains(this)) {
			student.getAnswers().add(this);
		}
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
		if(!question.getAnswers().contains(this)) {
			question.getAnswers().add(this);
		}
	}
	public Boolean getTrueFalseAnswer() {
		return trueFalseAnswer;
	}
	public void setTrueFalseAnswer(Boolean trueFalseAnswer) {
		this.trueFalseAnswer = trueFalseAnswer;
	}
	public int getMultipleChoiceAnswer() {
		return multipleChoiceAnswer;
	}
	public void setMultipleChoiceAnswer(int multipleChoiceAnswer) {
		this.multipleChoiceAnswer = multipleChoiceAnswer;
	}
	
	
	
}
