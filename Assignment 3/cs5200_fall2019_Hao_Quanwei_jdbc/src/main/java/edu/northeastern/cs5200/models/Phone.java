package edu.northeastern.cs5200.models;

public class Phone {
	private int id;
	private String phone;
	private Boolean primary;
	private Person person;
	
	public Phone() {
		super();
	}
	
	public Phone(String phone, Boolean primary, Person person) {
		super();
		this.phone = phone;
		this.primary = primary;
		this.person = person;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Boolean getPrimary() {
		return primary;
	}
	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
