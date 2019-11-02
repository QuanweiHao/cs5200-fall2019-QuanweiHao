package edu.northeastern.cs5200.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Enrollment {
	@EmbeddedId
	private EnrollmentKey id;
	
	@ManyToOne
	@JsonIgnore
	@MapsId("student_id")
	@JoinColumn(name = "student_id", referencedColumnName = "ID")
	private Student student;
	
	@ManyToOne
	@JsonIgnore
	@MapsId("section_id")
	@JoinColumn(name = "section_id", referencedColumnName = "ID")
	private Section section;
	
	private int grade;
	
	private String feedback;
	

	public Enrollment() {
		super();
	}
	

	public Enrollment(EnrollmentKey id, Student student, Section section) {
		super();
		this.id = id;
		this.student = student;
		this.section = section;
	}


	public EnrollmentKey getId() {
		return id;
	}


	public void setId(EnrollmentKey id) {
		this.id = id;
	}


	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
		if(!student.getEnrollments().contains(this)) {
			student.getEnrollments().add(this);
		}
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
		if(!section.getEnrollments().contains(this)) {
			section.getEnrollments().add(this);
		}
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	
}


