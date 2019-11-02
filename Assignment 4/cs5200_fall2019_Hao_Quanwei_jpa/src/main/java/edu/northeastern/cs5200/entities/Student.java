package edu.northeastern.cs5200.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Student extends Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int gradYear;
	private long scholarship;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
	private Set<Enrollment> enrollments;
	
	@OneToMany(mappedBy = "student")
	private List<Answer> answers;
	
	public Student() {
		
	}
	
	public Student(int id, String username, String password, String firstName, String lastName, int gradYear,
			long scholarship) {
		super(id, username, password, firstName, lastName);
		this.gradYear = gradYear;
		this.scholarship = scholarship;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getGradYear() {
		return gradYear;
	}
	public void setGradYear(int gradYear) {
		this.gradYear = gradYear;
	}
	public long getScholarship() {
		return scholarship;
	}
	public void setScholarship(long scholarship) {
		this.scholarship = scholarship;
	}
	

//	public List<Section> getEnrolledSections() {
//		return enrolledSections;
//	}
//
//	public void setEnrolledSections(List<Section> enrolledSections) {
//		this.enrolledSections = enrolledSections;
//	}
//	
//	public void enrollSection(Enrollment enrollment) {
//		   this.enrollments.add(enrollment);
//		   if(enrollment.getStudent() != this)
//		       enrollment.setStudent(this);
//	}
	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
		
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	
}
