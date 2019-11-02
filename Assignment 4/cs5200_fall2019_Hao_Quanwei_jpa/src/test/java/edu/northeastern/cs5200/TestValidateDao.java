package edu.northeastern.cs5200;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.northeastern.cs5200.daos.UniversityDao;
import edu.northeastern.cs5200.daos.ValidateDao;
import edu.northeastern.cs5200.entities.Course;
import edu.northeastern.cs5200.entities.Faculty;
import edu.northeastern.cs5200.entities.Section;
import edu.northeastern.cs5200.entities.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestValidateDao {
	@Autowired
	ValidateDao validateDao;
	
	@Autowired
	UniversityDao universityDao;
	
	@Test
	public void testValidateUsers() {
		long totalNumOfUsers = validateDao.NumOfUsers();
		System.out.print("The total number of users is ");
		System.out.println(totalNumOfUsers);
	}

	@Test
	public void testValidateFaculty() {
		long totalNumOfFacultys = validateDao.NumOfFacultys();
		System.out.print("The total number of faculties is ");
		System.out.println(totalNumOfFacultys);
	}
	
	@Test
	public void testValidateStudent() {
		long totalNumOfStudents = validateDao.NumOfStudents();
		System.out.print("The total number of students is ");
		System.out.println(totalNumOfStudents);
	}
	
	@Test 
	public void testValidateCourses() {
		long totalNumOfCourses = validateDao.NumOfCourses();
		System.out.print("The total number of courses is ");
		System.out.println(totalNumOfCourses);
	}
	
	@Test
	public void testValidateSections() {
		long totalNumOfSections = validateDao.NumOfSections();
		System.out.print("The total number of sections is ");
		System.out.println(totalNumOfSections);
	}
	
	@Test
	public void testValidateCourseAuthorship() {
		List<Faculty> faculties = universityDao.findAllFaculty();
		for(Faculty faculty : faculties) {
			int numOfCoursesAuthoredByFaculty = validateDao.NumOfCoursesAuthoredByFaculty(faculty);
			System.out.print("The total number of sections authored by " + faculty.getUsername() + " is ");
			System.out.println(numOfCoursesAuthoredByFaculty);
		}
	}
	
	@Test
	public void testValidateSectionPerCourse() {
		List<Course> courses = universityDao.findAllCourses();
		for(Course course : courses) {
			int numOfSectionsForCourse = validateDao.NumOfSectionsForCourse(course);
			System.out.print("The total number of sections of " + course.getLabel() + " is ");
			System.out.println(numOfSectionsForCourse);
		}
	}
	
	@Test
	public void testValidateSectionEnrollment() {
		List<Section> sections = universityDao.findAllSections();
		for(Section section : sections) {
			int numOfStudentsInSection = validateDao.NumOfStudentsInSection(section);
			System.out.print("The total number of students in " + section.getTitle() + " is ");
			System.out.println(numOfStudentsInSection);
		}
	}
	
	@Test
	public void testValidateStudentEnrollment() {
		List<Student> students = universityDao.findAllStudent();
		for(Student student : students) {
			int numOfSectionsForStudent = validateDao.NumOfSectionsForStudent(student);
			System.out.print("The total number of sections for " + student.getUsername() + " is ");
			System.out.println(numOfSectionsForStudent);
		}
	}
	
	@Test
	public void testValidateSectionSeat() {
		List<Section> sections = universityDao.findAllSections();
		for(Section section : sections) {
			int numSeats = validateDao.SeatsOfSection(section);
			System.out.print("The total number of seats of " + section.getTitle() + " is ");
			System.out.println(numSeats);
		}
	}

}
