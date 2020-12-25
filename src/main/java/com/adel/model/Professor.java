package com.adel.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Professor {


    @Id
    @Column(name = "professor_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    @Pattern(regexp = "[\\p{L} '-]+", message = "{com.adel.constraint.Name.message}")
    @Column(name = "first_name")
    private String firstName;


    @NotBlank
    @Pattern(regexp = "[\\p{L} '-]+", message = "{com.adel.constraint.Name.message}")
    @Column(name = "last_name")
    private String lastName;

    @OneToOne
    //@JoinColumn(name = "professor_id")
    private Course courses;

    public Professor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Course getCourses() {
        return courses;
    }

    public void setCourses(Course courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", courses=" + courses +
                '}';
    }
}

