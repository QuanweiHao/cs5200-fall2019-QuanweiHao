package edu.northeastern.cs5200.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.entities.Section;

public interface SectionRepository 
	extends CrudRepository<Section, Integer>{	
	@Query("SELECT s FROM Section s WHERE s.course = :id")
	public List<Section> findSectionsByCourseId(@Param("id") int id);
	
    @Query("SELECT s FROM Section s WHERE s.title=:title")
    public Section findSectionByTitle(@Param("title") String title);

}
