package edu.northeastern.cs5200.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.entities.Course;

public interface CourseRepository 
	extends CrudRepository<Course, Integer>{
	
	@Query("SELECT c FROM Course c WHERE c.faculty = :id")
	public List<Course> findCoursesByFacultyId(@Param("id") int id);
	
    @Query("SELECT c FROM Course c WHERE c.label=:label")
    public Course findCourseByLabel(@Param("label") String label);

}
