package com.example.demo.student;

import javax.persistence.*; // Make sure u always import this, so even if we switch from Hibernate ORM to another implementation - this all still works.
import java.time.LocalDate;
import java.time.Period;

@Entity // for Hibernate
@Table  // map this class to a table in our database
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    //private int age;

    @Transient  // This is NOT a field in our database: it is a field we calculate in our class.
    private int age;

    // empty contructor
    public Student() {
        this.id = id;
    }

    // constructor with EVERYTHING
    public Student(
            Long id,
            String name,
            String email,
            LocalDate dob
            //int age
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        //this.age = age;
    }

    // constructor WITHOUT id (the database will supply this for us)
    public Student(
            String name,
            String email,
            LocalDate dob
            //int age
    ) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        //this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getAge() {
        //return age;
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}

