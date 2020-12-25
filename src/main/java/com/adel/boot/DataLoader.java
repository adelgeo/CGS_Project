package com.adel.boot;

import com.adel.model.Student;
import com.adel.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component // to let spring find it and create a bean from it
public class DataLoader implements CommandLineRunner {

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Student student1 = new Student();
        student1.setFname("Adel");
        student1.setLname("Boudjema");
        student1.setAddress("B-8828 Avenue Henri-Julien");
        student1.setStudentEmail("aadel@hotmail.fr");
        student1.setMobile("+15146045832");

        studentRepository.save(student1);


    }
}
