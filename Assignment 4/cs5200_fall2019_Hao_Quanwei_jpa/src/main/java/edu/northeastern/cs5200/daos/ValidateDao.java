package edu.northeastern.cs5200.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.entities.Course;
import edu.northeastern.cs5200.entities.Faculty;
import edu.northeastern.cs5200.entities.Person;
import edu.northeastern.cs5200.entities.Section;
import edu.northeastern.cs5200.entities.Student;

@Repository
public class ValidateDao {
	@Autowired
	UniversityDao universityDao;
	
	public long NumOfUsers() {
		long totalNumOfUsers = 0;
		List<Person> users = universityDao.findAllUsers();
		totalNumOfUsers = users.size();
		return totalNumOfUsers;
	}
	
	public long NumOfFacultys() {
		long totalNumOfFacultys = 0;
		List<Faculty> facultys = universityDao.findAllFaculty();
		totalNumOfFacultys = facultys.size();
		return totalNumOfFacultys;
	}
	
	public long NumOfStudents() {
		long totalNumOfStudents = 0;
		List<Student> students = universityDao.findAllStudent();
		totalNumOfStudents = students.size();
		return totalNumOfStudents;
	}
	
	public long NumOfCourses() {
		long totalNumOfCourses = 0;
		List<Course> courses = universityDao.findAllCourses();
		totalNumOfCourses = courses.size();
		return totalNumOfCourses;
	}
	
	public long NumOfSections() {
		long totalNumOfSections = 0;
		List<Section> sections = universityDao.findAllSections();
		totalNumOfSections = sections.size();
		return totalNumOfSections;
	}
	
	public int NumOfCoursesAuthoredByFaculty(Faculty faculty) {
		int numOfCoursesAuthoredByFaculty = 0;
		List<Course> courses = universityDao.findCoursesForAuthor(faculty);
		numOfCoursesAuthoredByFaculty = courses.size();
		return numOfCoursesAuthoredByFaculty;
	}
	
	public int NumOfSectionsForCourse(Course course) {
		int numOfSectionsForCourse = 0;
		List<Section> sections = universityDao.findSectionForCourse(course);
		numOfSectionsForCourse = sections.size();
		return numOfSectionsForCourse;
	}
	
	public int NumOfStudentsInSection(Section section) {
		int numOfStudentsInSection = 0;
		List<Student> students = universityDao.findStudentsInSection(section);
		numOfStudentsInSection = students.size();
		return numOfStudentsInSection;
	}
	
	public int NumOfSectionsForStudent(Student student) {
		int numOfSectionsForStudent = 0;
		List<Section> sections = universityDao.findSectionsForStudent(student);
		numOfSectionsForStudent = sections.size();
		return numOfSectionsForStudent;
	}
	
	public int SeatsOfSection(Section section) {
		int numSeats = 0;
		numSeats = section.getSeats();
		return numSeats;
	}

}
