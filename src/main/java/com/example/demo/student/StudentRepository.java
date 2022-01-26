package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * DB-Access layer of our N-Tier app (API, Sevice, DB-Access)
 *
 * NOTE we are extending JpaRepository.
 * JpaRepository<T, ID> -extends-> PagingAndSortingRepository<T, ID> -extends-> CrudRepository<T, ID>
 *
 * 1. ...Repository in name in as standard for anything that accesses DATABASE or JPA
 * 2. Must be an interface.
 */
@Repository // means this is the DATA-ACCESS layer - just like @Controller (StudentController) was our API layer, @Service (StudentService) was our business-logic layer
public interface StudentRepository extends JpaRepository<Student /*T*/, Long /*ID*/> {

    // WITHOUT ANNOTATION Defaults to:
    // SELECT * FROM student WHERE email = ?;
    @Query("SELECT s FROM Student s WHERE s.email = ?1")   // equivalent to above default: clearer, more readable/intuitive.
        // NOTE: This is JBQL, not "straight" SQL.
    Optional<Student> findStudentByEmail(String email);
}
