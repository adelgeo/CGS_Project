package com.adel.controller;

import com.adel.model.Course;
import com.adel.model.Professor;
import com.adel.repository.CourseRepository;
import com.adel.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class ProfessorController {
    private CourseRepository courseRepository;
    private ProfessorRepository professorRepository;

    @Autowired
    public void professorRepository(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Autowired
    public void courseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @RequestMapping(path = "/professor/add", method = RequestMethod.GET)
    public String addProfessor(Model model) {
        model.addAttribute("professor", new Professor());

        List<Course> courseList = courseRepository.findAll();
        model.addAttribute("courseList", courseList);

        return "editprofessor";
    }

    @RequestMapping(path = "/professors", method = RequestMethod.POST)
    public String saveProfessor(@Validated Professor professor, BindingResult bindingResult, @RequestParam int selectId, Model model, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            List<Course> courseList = courseRepository.findAll();
            model.addAttribute("courseList", courseList);

            attributes.addFlashAttribute("classCss", "alert alert-warning");
            attributes.addFlashAttribute("message", "An unexpected error occurred! Please try again");


            return "editprofessor";
        } else {
            Course cs = courseRepository.findById(selectId).orElse(null);
            professor.setCourses(cs);
            professorRepository.save(professor);

            attributes.addFlashAttribute("classCss", "alert alert-success");
            attributes.addFlashAttribute("message", " The professor (" + professor.getLastName() + ") has been add to the Database !");
        }
        return "redirect:/professors";
    }

    @RequestMapping(path = "/professors", method = RequestMethod.GET)
    public String getAllProfessors(Model model) {
        model.addAttribute("professors", professorRepository.findAll());

        return "professors";
    }


    @RequestMapping(path = "/professors/edit/{id}", method = RequestMethod.GET)
    public String editProfessor(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("professor", professorRepository.findById(id));

        List<Course> courseList = courseRepository.findAll();
        model.addAttribute("courseList", courseList);

        return "editprofessor";
    }

    @RequestMapping(path = "/professor/delete/{id}", method = RequestMethod.GET)
    public String deleteProfessor(@PathVariable(value = "id") int id, RedirectAttributes attributes) {
        try {
            professorRepository.deleteById(id);
            attributes.addFlashAttribute("classCss", "alert alert-warning");
            attributes.addFlashAttribute("message", "One Professor deleted from the Database !");
        } catch (Exception e) {
            e.getMessage();
        }

        return "redirect:/professors";
    }

}
