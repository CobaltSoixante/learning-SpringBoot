package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * Add records to our student database PRIOR program startup.
 * Is magic in lieu of command-line parameters/stuff(?) - Just works.
 */
@Configuration  // Makes this a "configuration"(?) class.
public class StudentConfig {

    @Bean   // we add this annotation "so that this runs"?
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository // we're gonna inject a student repository here.
    ) {
        return args -> {
            Student mariam = new Student(
                    //1L,
                    "Mariam",
                    "mariam.jamal@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
                    //21
            );

            Student alex = new Student(
                    //1L,
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2004, Month.JANUARY, 5)
                    //21
            );

            studentRepository.saveAll(
                    List.of(mariam, alex)
            );

        };
    }
}
