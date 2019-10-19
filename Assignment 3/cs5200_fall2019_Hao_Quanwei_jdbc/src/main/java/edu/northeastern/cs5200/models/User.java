package edu.northeastern.cs5200.models;

import java.util.Date;

public class User extends Person {
	private Boolean userAggrement;
	
	public User(int id, String firstName, String lastName) {
		super(id, firstName, lastName);
	}

	public User(int id, String firstName, String lastName, String username, String password, String email, Date dob,
			Boolean userAggrement) {
		super(id, firstName, lastName, username, password, email, dob);
		this.userAggrement = userAggrement;
	}

	public Boolean getUserAggrement() {
		return userAggrement;
	}

	public void setUserAggrement(Boolean userAggrement) {
		this.userAggrement = userAggrement;
	}
	
}
