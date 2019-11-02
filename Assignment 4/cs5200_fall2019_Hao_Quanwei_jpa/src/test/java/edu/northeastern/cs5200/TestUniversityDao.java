package edu.northeastern.cs5200;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.northeastern.cs5200.daos.UniversityDao;
import edu.northeastern.cs5200.entities.Course;
import edu.northeastern.cs5200.entities.Faculty;
import edu.northeastern.cs5200.entities.Section;
import edu.northeastern.cs5200.entities.Student;
import edu.northeastern.cs5200.repository.CourseRepository;
import edu.northeastern.cs5200.repository.FacultyRepository;
import edu.northeastern.cs5200.repository.SectionRepository;
import edu.northeastern.cs5200.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUniversityDao{
	@Autowired
	UniversityDao universityDao;
	
	@Autowired
	FacultyRepository facultyRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	SectionRepository sectionRepository;
	
	@Test
	public void testTruncateDatabase() {
		universityDao.truncateDatabase();
	}
	
	@Test
	public void testCreateFaculty() {
		Faculty alan = new Faculty();
		alan.setUsername("alan");
		alan.setPassword("password");
		alan.setFirstName("Alan");
		alan.setLastName("Turin");
		alan.setOffice("123A");
		alan.setTenured(true);
		
		Faculty ada = new Faculty();
		ada.setUsername("ada");
		ada.setPassword("password");
		ada.setFirstName("Ada");
		ada.setLastName("Lovelace");
		ada.setOffice("123B");
		ada.setTenured(true);
		
		universityDao.createFaculty(alan);
		universityDao.createFaculty(ada);
	}
	
	@Test
	public void testCreateStudent() {
		Student alice = new Student();
		alice.setUsername("alice");
		alice.setPassword("password");
		alice.setFirstName("Alice");
		alice.setLastName("Wonderland");
		alice.setGradYear(2020);
		alice.setScholarship(12000);
		
		Student bob = new Student();
		bob.setUsername("bob");
		bob.setPassword("password");
		bob.setFirstName("Bob");
		bob.setLastName("Hope");
		bob.setGradYear(2021);
		bob.setScholarship(23000);
		
		Student charlie = new Student();
		charlie.setUsername("charlie");
		charlie.setPassword("password");
		charlie.setFirstName("Charlie");
		charlie.setLastName("Brown");
		charlie.setGradYear(2019);
		charlie.setScholarship(21000);
		
		Student dan = new Student();
		dan.setUsername("dan");
		dan.setPassword("password");
		dan.setFirstName("Dan");
		dan.setLastName("Craig");
		dan.setGradYear(2019);
		dan.setScholarship(0);
		
		Student edward = new Student();
		edward.setUsername("edward");
		edward.setPassword("password");
		edward.setFirstName("Edward");
		edward.setLastName("Scissorhands");
		edward.setGradYear(2022);
		edward.setScholarship(11000);
		
		Student frank = new Student();
		frank.setUsername("frank");
		frank.setPassword("password");
		frank.setFirstName("Frank");
		frank.setLastName("Herbert");
		frank.setGradYear(2018);
		frank.setScholarship(0);
		
		Student gregory = new Student();
		gregory.setUsername("gregory");
		gregory.setPassword("password");
		gregory.setFirstName("Gregory");
		gregory.setLastName("Peck");
		gregory.setGradYear(2023);
		gregory.setScholarship(10000);
		
		universityDao.createStudent(alice);
		universityDao.createStudent(bob);
		universityDao.createStudent(charlie);
		universityDao.createStudent(dan);
		universityDao.createStudent(edward);
		universityDao.createStudent(frank);
		universityDao.createStudent(gregory);
	}
	
	@Test
	public void testCreateCourse() {
		Faculty alan = facultyRepository.findFacultyByUsername("alan");
		Faculty ada = facultyRepository.findFacultyByUsername("ada");
		
		Course cs1234 = new Course();
		cs1234.setLabel("CS1234");
		universityDao.createCourse(cs1234);
		universityDao.setAuthorForCourse(alan, cs1234);
		
		Course cs2345 = new Course();
		cs2345.setLabel("CS2345");
		universityDao.createCourse(cs2345);
		universityDao.setAuthorForCourse(alan, cs2345);
		
		Course cs3456 = new Course();
		cs3456.setLabel("CS3456");
		universityDao.createCourse(cs3456);
		universityDao.setAuthorForCourse(ada, cs3456);
		
		Course cs4567 = new Course();
		cs4567.setLabel("CS4567");
		universityDao.createCourse(cs4567);
		universityDao.setAuthorForCourse(ada, cs4567);
	}
	
	@Test
	public void testCreateSection() {
		Course cs1234 = courseRepository.findCourseByLabel("CS1234");
		Course cs2345 = courseRepository.findCourseByLabel("CS2345");
		Course cs3456 = courseRepository.findCourseByLabel("CS3456");
		
		Section sec4321 = new Section();
		sec4321.setTitle("SEC4321");
		sec4321.setSeats(50);
		universityDao.createSection(sec4321);
		
		
		Section sec5432 = new Section();
		sec5432.setTitle("SEC5432");
		sec5432.setSeats(50);
		universityDao.createSection(sec5432);
		
		Section sec6543 = new Section();
		sec6543.setTitle("SEC6543");
		sec6543.setSeats(50);
		universityDao.createSection(sec6543);
		
		
		Section sec7654 = new Section();
		sec7654.setTitle("SEC7654");
		sec7654.setSeats(50);
		universityDao.createSection(sec7654);
		
		
		universityDao.addSectionToCourse(sec4321, cs1234);
		universityDao.addSectionToCourse(sec5432, cs1234);
		universityDao.addSectionToCourse(sec6543, cs2345);
		universityDao.addSectionToCourse(sec7654, cs3456);
	}
	
	@Test
	public void testEnrollStudentToSection() {
		Student alice = studentRepository.findStudentByUsername("alice");
		Student bob = studentRepository.findStudentByUsername("bob");
		Student charlie = studentRepository.findStudentByUsername("charlie");
		
		Section sec4321 = sectionRepository.findSectionByTitle("SEC4321");
		Section sec5432 = sectionRepository.findSectionByTitle("SEC5432");
		Section sec6543 = sectionRepository.findSectionByTitle("SEC6543");
		
		universityDao.enrollStudentInSection(alice, sec4321);
		universityDao.enrollStudentInSection(alice, sec5432);
		universityDao.enrollStudentInSection(bob, sec5432);
		universityDao.enrollStudentInSection(charlie, sec6543);
		
	}
}
