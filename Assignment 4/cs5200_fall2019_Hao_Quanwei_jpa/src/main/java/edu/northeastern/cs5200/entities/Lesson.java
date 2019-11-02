package edu.northeastern.cs5200.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Lesson {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String label;
	@ManyToOne
	@JsonIgnore
	private Module module;
	@OneToMany(mappedBy = "lesson")
	private List<Topic> topices;
	
	public Lesson() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
		if(!module.getLessons().contains(this)) {
			module.getLessons().add(this);
		}
	}

	public List<Topic> getTopices() {
		return topices;
	}

	public void setTopices(List<Topic> topices) {
		this.topices = topices;
	}
	

}
