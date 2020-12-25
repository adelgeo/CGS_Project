package com.adel.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int courseId;

    @NotNull(message = "Please enter course name")
    @NotEmpty(message = "Please enter course name")
    @Column(name = "name", length = 100)
    private String name;

    @OneToOne
    private Professor professor;

    @NotNull(message = "Please enter course name")
    @Size(min = 6, max = 6, message = "Code name should be 6 characters")
    @Column(length = 6)
    private String code;

    @NotNull(message = "Please enter description")
    @NotEmpty(message = "Please enter description")
    private String description;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", name='" + name + '\'' +
                ", professor=" + professor +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
