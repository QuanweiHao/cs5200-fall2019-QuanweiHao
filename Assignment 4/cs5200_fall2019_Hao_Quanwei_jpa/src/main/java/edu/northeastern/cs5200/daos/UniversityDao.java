package edu.northeastern.cs5200.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.entities.Course;
import edu.northeastern.cs5200.entities.Enrollment;
import edu.northeastern.cs5200.entities.EnrollmentKey;
import edu.northeastern.cs5200.entities.Faculty;
import edu.northeastern.cs5200.entities.Person;
import edu.northeastern.cs5200.entities.Section;
import edu.northeastern.cs5200.entities.Student;
import edu.northeastern.cs5200.repository.CourseRepository;
import edu.northeastern.cs5200.repository.EnrollmentRepository;
import edu.northeastern.cs5200.repository.FacultyRepository;
import edu.northeastern.cs5200.repository.PersonRepository;
import edu.northeastern.cs5200.repository.SectionRepository;
import edu.northeastern.cs5200.repository.StudentRepository;

@Repository
public class UniversityDao {
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private FacultyRepository facultyRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private SectionRepository sectionRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	public void truncateDatabase() {
		enrollmentRepository.deleteAll();
		sectionRepository.deleteAll();
		studentRepository.deleteAll();
		courseRepository.deleteAll();
		facultyRepository.deleteAll();
		personRepository.deleteAll();
	}
	
	public Faculty createFaculty(Faculty faculty) {
		return facultyRepository.save(faculty);
	}
	
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}
	
	public void createSection(Section section) {
		sectionRepository.save(section);
	}
	
	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}
	
	public Course addSectionToCourse(Section section, Course course) {
		section.setCourse(course);
		sectionRepository.save(section);
		return course;
	}

	
	public Course setAuthorForCourse(Faculty faculty, Course course) {
		course.setFaculty(faculty);
		return courseRepository.save(course);
		
	}
	
	public Boolean enrollStudentInSection(Student student, Section section) {
		int seats = section.getSeats();
		if (seats == 0) {
			return false;
		}
		Enrollment enrollment = new Enrollment();
		EnrollmentKey id = new EnrollmentKey();
		id.setStudent(student);
		id.setSection(section);
		enrollment.setId(id);
		enrollment.setSection(section);
		enrollment.setStudent(student);
		
		enrollmentRepository.save(enrollment);
		seats--;
		section.setSeats(seats);
		sectionRepository.save(section);
		return true;
	}
	
	public List<Person> findAllUsers(){
		return (List<Person>)personRepository.findAll();
	}
	
	public List<Faculty> findAllFaculty(){
		return (List<Faculty>)facultyRepository.findAll();
	}
	
	public List<Student> findAllStudent(){
		return (List<Student>)studentRepository.findAll();
	}
	
	public List<Course> findAllCourses(){
		return (List<Course>)courseRepository.findAll();
	}
	
	public List<Section> findAllSections(){
		return (List<Section>)sectionRepository.findAll();
	}
	
	public List<Course> findCoursesForAuthor(Faculty faculty){
		return faculty.getCourses();
	}
	
	public List<Section> findSectionForCourse(Course course){
		return course.getSections();
	}
	
	public List<Student> findStudentsInSection(Section section){
		Set<Enrollment> enrollments = section.getEnrollments();
		List<Student> students = new ArrayList<>();
		for (Enrollment enrollment : enrollments) {
			Student student = enrollment.getStudent();
			students.add(student);
		}
		return students;
		
	}
	
	public List<Section> findSectionsForStudent(Student student){
		Set<Enrollment> enrollments = student.getEnrollments();
		List<Section> sections = new ArrayList<>();
		for (Enrollment enrollment : enrollments) {
			Section section = enrollment.getSection();
			sections.add(section);
		}
		return sections;
	}
	
}

