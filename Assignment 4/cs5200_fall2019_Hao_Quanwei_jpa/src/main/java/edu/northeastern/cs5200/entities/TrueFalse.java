package edu.northeastern.cs5200.entities;

import javax.persistence.Entity;

@Entity
public class TrueFalse extends Question {
	private Boolean isTrue;
	
	
	public TrueFalse() {
		super();
	}

	public Boolean getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(Boolean isTrue) {
		this.isTrue = isTrue;
	}
	

}
