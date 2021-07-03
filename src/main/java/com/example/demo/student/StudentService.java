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
//@Component  // tell Spring that this is a bean
@Service    // EXACTLY like saying '@Component', but - for readability - makes it clear the this is a "Service"  class in our N-Tier hierarchy.
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    // I think that 'Autowired' implies a 'new' tho the constructor parameters (much like scalar variables are implicitly initializae to default values).
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
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
        studentRepository.save(student);
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
            student.setName(name);
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
            student.setEmail(email);
        }
    }
}