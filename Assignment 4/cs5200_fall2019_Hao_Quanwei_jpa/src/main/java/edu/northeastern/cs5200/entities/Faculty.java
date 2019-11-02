package edu.northeastern.cs5200.entities;

import java.util.List;

import javax.persistence.*;

@Entity
public class Faculty extends Person {
	private String office;
	private Boolean tenured;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "faculty")
	private List<Course> courses;

	public Faculty() {
	}
	

	public Faculty(int id, String username, String password, String firstName, String lastName, String office,
			Boolean tenured) {
		super(id, username, password, firstName, lastName);
		this.office = office;
		this.tenured = tenured;
	}


	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public Boolean getTenured() {
		return tenured;
	}
	public void setTenured(Boolean tenured) {
		this.tenured = tenured;
	}
	public List<Course> getCourses() {
		return courses;
	}


	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
}
