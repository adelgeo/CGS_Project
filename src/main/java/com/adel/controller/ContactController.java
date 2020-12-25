package com.adel.controller;


import com.adel.boot.MailComponent;
import com.adel.model.Student;
import com.adel.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ContactController {

    @Autowired
    MailComponent mailComponent;
    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/email/contact/{id}")
    public String index(Model model, @ModelAttribute Student student, @PathVariable(value = "id") String id) { //attribute contact injected
        model.addAttribute("student", studentRepository.findById(id));
        return "send";
    }

    @GetMapping("/email/contact/")
    public String emailValidation(Model model, @ModelAttribute Student student) {

        return "send";
    }


    @PostMapping("/post")
    public String processFormContact(@Validated Student student, BindingResult bindingResult, RedirectAttributes model) {
        if (bindingResult.hasErrors()) {
            return "send";
        }

        if (mailComponent.sendHtmlMail(student)) {// Replaced sendSimpleMail(contact)
            model.addFlashAttribute("classCss", "alert alert-success");
            model.addFlashAttribute("message", "Your message has been sent");
        } else {
            model.addFlashAttribute("classCss", "alert alert-warning");
            model.addFlashAttribute("message", "An unexpected error occurred! Please try later");
        }

        return "redirect:/email/contact/";
    }
}
