package com.example.springmvc.boot;

import com.example.springmvc.model.Student;
import com.example.springmvc.repository.StudentRepository;
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
        student1.setName("Milky Bar");
        student1.setDescription("With chocolate");
        student1.setType("CANDIES");
        student1.setCategory("BARS");
        student1.setPrice(3.99);

        studentRepository.save(student1);

        Student student2 = new Student();
        student2.setName("Almond Bar");
        student2.setDescription("With chocolate and honey");
        student2.setType("CANDIES");
        student2.setCategory("BARS");
        student2.setPrice(4.99);

        studentRepository.save(student2);



    }
}
