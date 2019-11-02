package edu.northeastern.cs5200.entities;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Section {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int seats;
	private String title;
	@ManyToOne
	@JsonIgnore
	private Course course;
	@OneToMany(fetch = FetchType.EAGER, mappedBy="section")
	private Set<Enrollment> enrollments;
	
	public Section() {
		super();
	}
	
	public Section(int id, int seats, Course course) {
		super();
		this.id = id;
		this.seats = seats;
		this.course = course;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
		if(!course.getSections().contains(this)) {
			course.getSections().add(this);
		}
	}

	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
	
//	public List<Student> getEnrolledStudents() {
//		return enrolledStudents;
//	}
//
//	public void setEnrolledStudents(List<Student> enrolledStudents) {
//		this.enrolledStudents = enrolledStudents;
//	}
//
//	public void enrollStudent(Student student) {
//	   this.enrolledStudents.add(student);
//	   if(!student.getEnrolledSections().contains(this)) {
//	       student.getEnrolledSections().add(this);
//	   }
//	}

}
