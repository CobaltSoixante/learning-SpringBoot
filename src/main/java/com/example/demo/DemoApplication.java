package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import student.Student;

//import java.time.LocalDate;
//import java.time.Month;
//import java.util.List;

/**
 * The course I adapted this from is on:
 * https://www.youtube.com/watch?v=9SGDpanrc8U
 * Simple 3 tier example:
 * API: StudentController.java
 * Logic: StudentService.java
 * Database Access: StudentRepository.java
 * ----------------------
 * CRM base class: Student.java
 * Initial Config: StudentConfig.java
 *
 * In order to "port existing project to github" my guide is the very 1st link on google-search:
 * https://docs.github.com/en/github/importing-your-projects-to-github/importing-source-code-to-github/adding-an-existing-project-to-github-using-the-command-line
 *
 * This is a web-application. Spring-boot seems to have given me "Tomcat" as a default server/servlet-server,
 * that opens a port on 8080.
 *
 * This entire course gives ALL the Java basics - except the frontend/HTML client - for that us postgreSQL &/or POSTMAN: I used POSTMAN.
 * (if I were using Intellij "ultimate" edition I wouldn't need POSTMAN, but "ultimate" costs money past the trial -
 * and I already have POSTMAN "know-how", and installed on my computer).
 * HOWEVER, note: on Jan 2022 I am noticing changes: must stick with Postman V6: V7 and above are TEAM versions that require payment.
 *
 * This springboot based app only only happens to be a web[server] app.
 * In "spring initializr" springboot repository we select the following 3 components to correspond to a N-tier webapp:
 * (1) Spring Web (with Tomcat based Java servlet server as default, which also provides REST (GET/PUT(update)/POST(add)/DELETE) functionality)
 * w. MVC (N-Tier classes: @RestController, @Service, @Repository).
 * IE our CLASSES StudentController/Service/Repository classes AND
 * in the StudentController class - METHODS for: @GetMapping/PutMapping/UpdateMapping/DeleteMapping
 * (2) Sping Data Jpa - uses Spring Data & Hibernate
 * IE our STudent class.
 * (3) PostgreSQL Driver - to transparently support Postgres as our database of choice (as opposed to H2, Mongo, ElasticSearch & others).
 *
 * NOTES:
 *
 * postgreSQL is strange, I have got it set up here so that in order to access the POSTGRES terminal/shell, I have to do:
 * cd C:\Program Files\PostgreSQL\13\bin
 * //psql -U amigoscode student
 * //(where 'amigiscode' is the username/rollname, 'student' is my database name; the password is 'password').
 * psql -U postgres
 *
 * Spring/annotation JPA takeaways / N-TIER layer:
 *
 * @RestController on StudentController - implies this is the API layer, providing interfaces [= Restful nouns]:
 * GET (get existing records/resources), PUT (update records), POST (save/ADD new records/resources), DELETE (delete records).
 * @RequestMapping on StudentController - implies additional path mapping to URL, IE localhost:8080/additional-pathname
 * @GetMapping on StudentController.getStudents - implies this will be the method used to serve GET requests.
 *
 * @Service on StudentService - implies is the Service layer. (@Component would suffice, but this makes it clearere).
 *
 * @Repository on StudentRepository - implies this id the DATABASE-ACCESS layer.
 *
 * @Autowired - am working this out: seems to imply that this bean should be instantiated BY-TYPE/DEFAULT, without need for the 'new' keyword - just like scalars.
 *
 * N-tier architecture:
 * API layer ( GET(=SELECT) PUT(=UPDATE) POST(=INSERT/ADD) DELETE(=DELETE) )
 * Service Layer
 * Data Access Layer
 *
 * aw: This project was initially created using "Spring Initializr" (https://start.spring.io/) site -
 * a primary site for creating Spring-Boot projects.
 *
 * It seems to me that SpringBoot (via spring initializr) is a fashionable alternative to using the standard default Eclipse-Maven archetypes
 * when creating a new project - and possibly somewhat "superior":
 * instead of selecting a single initial archetype that is a "best-fit" and tack on individual components as you go along -
 * with spring-boot you select the PARENT spring-boot version <artifactId>springboot-starter-version</artifactId> w. v. 2.4.1, and then selects all the components that you need;
 * these components are placed in your pom.xml file without explicit version numbers
 * (they are presumably deduced from the "PARENT spring-boot version").
 *
 * I am using IntelliJ as the IDE for this - as per the YOUTUBE video at the top,
 * though I reckon that after creating and extracting the initial project via the Spring Initializr site -
 * Eclipse would do the job as well.
 *
 */
@SpringBootApplication	// Bare bone SpringBootApplication
//@RestController	// aw: make this class serve as rest mapping for endpoints (?)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

// All the following code was just for introduction - prioe to beefing out our 'StudentController' class/

//	/**
//	 * just type:
//	 * http://localhost:8080/
//	 * and - tada - magic: prints "Hello World" on my web browser.
//	 * @return
//	 */
//	@GetMapping	// aw: Make this method a "restful" endpoint on port 8080: our ONLY REST endpoint for now...
////	public String hello()
////	{
////		return "Hello World";
////	}
//	/**
//	 * Gives a sorta JSON representation of:
//	 * "Hello",
//	 * "World"
//	 */
////	public List<String> hello()
////	{
////		return List.of("Hello", "World");
////	}
//	public List<Student> hello()
//	{
//		return List.of(
//				new Student(
//						1L,
//						"Mariam",
//						"mariam.jamal@gmail.com",
//						LocalDate.of(2000, Month.JANUARY, 5),
//						21
//				)
//		);
//	}

}
