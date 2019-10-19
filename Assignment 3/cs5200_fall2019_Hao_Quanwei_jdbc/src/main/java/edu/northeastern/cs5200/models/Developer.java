package edu.northeastern.cs5200.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Developer extends Person {
	private String developerKey;
	private Collection<Website> websites = new ArrayList<>();

	public Developer(int id, String firstName, String lastName, String developerKey) {
		super(id, firstName, lastName);
		this.developerKey = developerKey;
	}	

	public Developer(int id, String firstName, String lastName, String username, String password, String email,
			Date dob, String developerKey) {
		super(id, firstName, lastName, username, password, email, dob);
		this.developerKey = developerKey;
	}

	public Developer(int id, String firstName, String lastName, String username, String password, String email,
			Date dob, Collection<Phone> phone, Collection<Address> address, String developerKey) {
		super(id, firstName, lastName, username, password, email, dob, phone, address);
		this.developerKey = developerKey;
	}

	public String getDeveloperKey() {
		return developerKey;
	}

	public void setDeveloperKey(String developerKey) {
		this.developerKey = developerKey;
	}
	public Collection<Website> getWebsites() {
		return websites;
	}

	public void setWebsites(Collection<Website> websites) {
		this.websites = websites;
	}
	
	public void addWebsites(Website website) {
		this.websites.add(website);
	}
	
	public void removeWebsites(Website website) {
		this.websites.remove(website);
	}
}
