package com.adel.controller;

import com.adel.model.Course;
import com.adel.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    public void courseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @RequestMapping(path = "/courses/add", method = RequestMethod.GET)
    public String addCourse(Model model) {
        model.addAttribute("course", new Course());
        return "editcourse";
    }

    @RequestMapping(path = "/courses", method = RequestMethod.POST)
    public String saveCourse(@Validated Course course, BindingResult bindingResult, RedirectAttributes model) {
        if (bindingResult.hasErrors()) {
            return "editcourse";
        } else {
            courseRepository.save(course);
            model.addFlashAttribute("classCss", "alert alert-success");
            model.addFlashAttribute("message", " The course (" + course.getName() + ") has been add to the Database !");
        }
        return "redirect:/courses";
    }


    @RequestMapping(path = "/courses", method = RequestMethod.GET)
    public String getAllCourses(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        return "courses";
    }


    @RequestMapping(path = "/courses/edit/{id}", method = RequestMethod.GET)
    public String editCourse(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("course", courseRepository.findById(id));
        return "editcourse";
    }

    @RequestMapping(path = "/courses/delete/{id}", method = RequestMethod.GET)
    public String deleteCourse(@PathVariable(value = "id") int id, RedirectAttributes model) {
        try {
            courseRepository.deleteById(id);
            model.addFlashAttribute("classCss", "alert alert-warning");
            model.addFlashAttribute("message", "One Course deleted from the Database !");
        } catch (Exception e) {
            e.getMessage();

        }

        return "redirect:/courses";
    }


}
