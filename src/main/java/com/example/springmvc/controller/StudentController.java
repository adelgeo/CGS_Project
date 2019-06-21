package com.example.springmvc.controller;

import com.example.springmvc.model.Student;
import com.example.springmvc.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentController {

    private StudentRepository studentRepository;

    @Autowired
    public void productRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping(path = "/")
    public String index() {

        return "index";
    }

    @RequestMapping(path = "/students/add", method = RequestMethod.GET)
    public String createProduct(Model model) {
        model.addAttribute("student", new Student());
        return "edit";
    }

    @RequestMapping(path = "/students", method = RequestMethod.POST)
    public String saveProduct(Student student) {
        studentRepository.save(student);
        return "redirect:/";
    }

    @RequestMapping(path = "/students", method = RequestMethod.GET)
    public String getAllProducts(Model model){
        model.addAttribute("students", studentRepository.findAll());
        return "students";
    }

    @RequestMapping(path="/students/edit/{id}", method = RequestMethod.GET)
    public String editProduct(Model model, @PathVariable(value="id") String id){
        model.addAttribute("student", studentRepository.findById(id));
        return "edit";
    }
    @RequestMapping(path="/students/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable(value="id") String id){
        studentRepository.deleteById(id);
        return "redirect:/students";
    }

}
