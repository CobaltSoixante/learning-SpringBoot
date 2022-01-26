package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Contains stuff for our Service (business logic) LAYER (in the N-Tier API-Service-DataAccess hierarchy).
 *
 */
//@Component  // tell Spring that this is a BEAN - a class that MUST be instantiated when used (much like a typical scalar, I guess)
@Service    // EXACTLY like saying '@Component', but - for readability - makes it clear the this is a "Service"  class in our N-Tier hierarchy.
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired  // facilitates dependency-injection [into StudentController]. (50:44/1:37:30)
    // I think that 'Autowired' implies a 'new' to the constructor parameters (much like scalar variables are implicitly initialized to default values).
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        // (51:00/1:37:30)
        return studentRepository.findAll(); // Spring Data JPA magic: we didn't need to implement .findall or anything in StudentRepository class (51:30)
/*
        return List.of(
                new Student(
                        1L,
                        "Mariam",
                        "mariam.jamal@gmail.com",
                        LocalDate.of(2000, Month.JANUARY, 5),
                        21
                )
        );
*/
    }

    public void addNewStudent(Student student) {
        // Our business logic: we want to save student ONLY if he/she not present:
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);    // If student is not present in table - we save it. :-)
        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(
                    "student with id " + studentId + " does not exist"
            );
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional  // New annotation: means we don't need to use any JPQL code,
    // the method relies on setters from our Student ORM class.
    // THUS, because of the magical '@Transactional' annotation - we are spared the necessity of doing queries.
    // @Transactional here [at the Service/business] layer causes "@Entity Student" to go into a managed state, so no queries or JPQL code is necessary. Learn more about @Transactional in this guy's spring-data-JPA course.
    public void updateStudent(Long studentId,
                              String name,
                              String email) {
        /*
        Perform simple business logic.
         */
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + " does not exist."));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(student.getName(), name)
        ) {
            student.setName(name);  // Update student name
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)) {
            // Check that email hasn't been taken.
            Optional<Student> studentOptional = studentRepository
                    .findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);    // Update student email.
        }
    }
}