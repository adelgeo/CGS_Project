package com.adel.controller;

import com.adel.boot.MailComponent;
import com.adel.repository.StudentRepository;
import com.adel.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StudentController {

    @Autowired
    MailComponent mailComponent;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    public void studentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping(path = "/")
    public String index() {

        return "index";
    }

    @RequestMapping(path = "/students/add", method = RequestMethod.GET)
    public String createStudent(Model model) {//@Validated Student student, BindingResult bindingResult
        model.addAttribute("student", new Student());

        return "edit";
    }

    @RequestMapping(path = "/students", method = RequestMethod.POST)
    public String saveStudent(@Validated Student student, BindingResult bindingResult, RedirectAttributes model) {

        if (bindingResult.hasErrors()) {

            model.addFlashAttribute("classCss", "alert alert-warning");
            model.addFlashAttribute("message", "An unexpected error occurred! Please try again");

            return "edit";
        } else {
            studentRepository.save(student);
            mailComponent.sendConfirmationMail(student);
            model.addFlashAttribute("classCss", "alert alert-success");
            model.addFlashAttribute("message", " The Student | " + student.getFname() + " " + student.getLname().toUpperCase() +
                    " | has been add to the Database and an email has been sent !");

        }
        return "redirect:/students";
    }

    @RequestMapping(path = "/students", method = RequestMethod.GET)
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "students";
    }

    @RequestMapping(path = "/students/edit/{id}", method = RequestMethod.GET)
    public String editStudent(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("student", studentRepository.findById(id));
        return "edit";
    }

    @RequestMapping(path = "/students/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable(value = "id") String id, RedirectAttributes model) {
        try {
            studentRepository.deleteById(id);

            //handle exception here if an error occur when deleting from DB

            model.addFlashAttribute("classCss", "alert alert-warning");
            model.addFlashAttribute("message", "Student deleted from the Database !");
        } catch (Exception e) {
            e.getMessage();
        }
        return "redirect:/students";
    }

}
