package edu.northeastern.cs5200.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.entities.Student;

public interface StudentRepository 
	extends CrudRepository<Student, Integer>{
//	@Query("SELECT p FROM Person p WHERE p.dtype = Student")
//	public List<Student> findAllStudents();
	
 	@Query("SELECT p FROM Person p WHERE p.username=:username")
    public Student findStudentByUsername(@Param("username") String username);

//    @Query("SELECT p FROM Person p WHERE p.username=:username AND p.password=:password")
//    public Student findStudentByCredentials(@Param("username") String username, @Param("password") String password);

}
