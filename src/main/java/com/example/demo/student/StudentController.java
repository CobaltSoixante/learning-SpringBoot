package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * Contains all the resources for our API LAYER (in the N-Tier API-Service-DataAccess hierarchy).
 *
 * A bit much magic happening here for my comfort zone:
 * (1) This class must exist in a sub-package  of where the main 'DemoApplication' resides - or the services/API's it exposes (Get, etc) are not exposed on the internet...
 * (2) ... But, magically, these services are exposed without my having manually instantiated a concreten instance of the class.
 * (3) And: note how the method 'getStudents()' could just have as easily been named 'x()': no one cares!
 *
 * NOTE that this tutorial does not go into the FORMS required for GET/POST/DELETE/PUT -
 * I use PostgreSql and POSTMAN as clients.
 */
@RestController    // aw: make this class serve as REST mapping [in the API layer] for endpoints (?).
// Indicates that this [server/[tomcat]-based] class will serve REST requests from HTTP client (GET/PUT/UPDATE/POST);
// XXXX indicates that this is an API layer class in our N-Tier heirarchy
// (Just as as @Service with StudentService makes it clear that it a a Service-Layer tier class/bean).
@RequestMapping(path = "api/v1/student")    // == http://localhost:8080/api/v1/student
public class StudentController {

	private final StudentService studentService;

	@Autowired	// Spring will automatically instantiate the constructor parameter so everything can work.
	// But: for this the class StudentService must be annotated with '@Component' to make it a bean.
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	/*
 	 * to test: from POSTMAN - 'GET' with path:
	 * localhost:8080/api/v1/student/1
	*/
	@GetMapping // aw: Make this method a "restful" GET(?) endpoint on port 8080: our ONLY REST endpoint for now...
	public List<Student> getStudents()
	{
		return studentService.getStudents();
//		return List.of(
//				new Student(
//						1L,
//						"Mariam",
//						"mariam.jamal@gmail.com",
//						LocalDate.of(2000, Month.JANUARY, 5),
//						21
//				)
//		);
	}

	/**
	 * I am NOT using the Intellij "ultimate version", just "community",
	 * so I don't test this within Intellij, I use "POSTMAN" with following stuff:
	 * POST
	 * localhost:8080/api/v1/student
	 * JSON (application/json)
	  {
	  	"name": "Bilal",
	  	"email" : "bilal.ahmed@gmail.com",
	  	"dob": "1995-12-17"
	  }
	 * and - Tada! Works!
	 *
	 * @param student
	 */
	@PostMapping	// Add new student to database.
	public void addNewStudent(@RequestBody Student student)	// @RequestBody = grab the 'student' parameter data from the RequestBody.
	{
		studentService.addNewStudent(student);
	}

	/**
	 *
	 * @param studentId
	 *
	 * to test: from POSTMAN - 'DELETE' with path:
	 * localhost:8080/api/v1/student/1
	 *
	 * '1' is the 'PathVariable' here - I think there can be only one?
	 */
	@DeleteMapping(path = "{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long studentId)
		//@PathVariable means we are grabbing the path variable.
		// Grabbing stuff from the path is EASY.
	{
		studentService.deleteStudent(studentId);
	}


	/**
	 * PUT: Update a resource from the database.
	 *
	 * to test: from POSTMAN - 'PUT' with path & request/form parameters:
	 * localhost:8080/api/v1/student/1?name=Maria&email=maria@gmail.com
	 *
	 * '1' is the 'PathVariable' here - I think there can be only one?
	 * I notice that the "machinery" is very finicky:
	 * "studentId" must be specified in BOTH @PutMapping and @PathVariable annotations: whatcha gonna do: magic.
	 *
 	 * @param studentId
	 * @param name
	 * @param email
	 */
	@PutMapping(path = "{studentId}")
	public void updateStudent(
			@PathVariable("studentId") Long studentId,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email
	)
	//@PathVariable means we are grabbing the path variable.
	// Grabbing stuff from the path is EASY.
	//@RequestParam - I am assuming this is a FORM variable (which also gets pushed onto the path).
	{
		studentService.updateStudent(studentId, name, email);
	}
}
