package edu.northeastern.cs5200.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.entities.Faculty;

public interface FacultyRepository 
	extends CrudRepository<Faculty, Integer>{
//	@Query("SELECT p FROM Person p WHERE p.dtype = Faculty")
//	public List<Faculty> findAllFacultys();
	
	@Query("SELECT p FROM Person p WHERE p.username=:username")
    public Faculty findFacultyByUsername(@Param("username") String username);

//    @Query("SELECT p FROM Person p WHERE p.username=:username AND p.password=:password")
//    public Faculty findFacultyByCredentials(@Param("username") String username, @Param("password") String password);
}
