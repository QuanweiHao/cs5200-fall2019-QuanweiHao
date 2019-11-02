package edu.northeastern.cs5200.repository;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.entities.Enrollment;

public interface EnrollmentRepository 
extends CrudRepository<Enrollment, Integer>{
	

}
