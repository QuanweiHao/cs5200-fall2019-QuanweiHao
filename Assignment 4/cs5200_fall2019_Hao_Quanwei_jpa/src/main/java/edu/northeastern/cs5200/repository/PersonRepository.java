package edu.northeastern.cs5200.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.entities.Person;

public interface PersonRepository 
	extends CrudRepository<Person, Integer>{
    @Query("SELECT p FROM Person p WHERE p.username=:username")
    public Person findPersonByUsername(@Param("username") String username);

//    @Query("SELECT p FROM Person p WHERE p.username=:username AND p.password=:password")
//    public Person findPersonByCredentials(@Param("username") String username, @Param("password") String password);
}
