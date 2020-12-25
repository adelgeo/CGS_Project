package com.adel.controller;

import com.adel.model.Course;
import com.adel.model.Grade;
import com.adel.model.Student;
import com.adel.repository.CourseRepository;
import com.adel.repository.GradeRepository;
import com.adel.repository.StudentRepository;
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
public class GradeController {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    public void gradeRepository(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    public void courseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    public void studentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @RequestMapping(path = "/grade/add", method = RequestMethod.GET)
    public String addGrade(Model model) {
        model.addAttribute("grade", new Grade());

        List<Grade> gradeList = gradeRepository.findAll();
        model.addAttribute("gradeList", gradeList);

        List<Student> studentList = studentRepository.findAll();
        model.addAttribute("studentList", studentList);

        List<Course> courseList = courseRepository.findAll();
        model.addAttribute("courseList", courseList);

        return "editgrade";
    }

    @RequestMapping(path = "/grade/add/{id}", method = RequestMethod.GET)
    public String addMark(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("grade", new Grade());

        List<Grade> gradeList = gradeRepository.findAll();
        model.addAttribute("gradeList", gradeList);

        List<Student> studentList = studentRepository.findAll();
        model.addAttribute("studentList", studentList);

        List<Course> courseList = courseRepository.findAll();
        model.addAttribute("courseList", courseList);

        return "editgrade";
    }

    @RequestMapping(path = "/grades", method = RequestMethod.POST)
    public String saveGrade(@Validated Grade grade, BindingResult bindingResult, @RequestParam String selectStudent, @RequestParam int selectCourse, @RequestParam int selectSemester, @RequestParam String selectYear, Model model, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {

            List<Grade> gradeList = gradeRepository.findAll();
            model.addAttribute("gradeList", gradeList);

            List<Student> studentList = studentRepository.findAll();
            model.addAttribute("studentList", studentList);

            List<Course> courseList = courseRepository.findAll();
            model.addAttribute("courseList", courseList);

            attributes.addFlashAttribute("classCss", "alert alert-warning");
            attributes.addFlashAttribute("message", "An unexpected error occurred! Please try again");

            return "editgrade";
        } else {

            Student st = studentRepository.findById(selectStudent).orElse(null);
            Course cs = courseRepository.findById(selectCourse).orElse(null);

            grade.setSemester(selectSemester);
            grade.setCourse(cs);
            grade.setStudent(st);
            grade.setYear(selectYear);

            gradeRepository.save(grade);
            attributes.addFlashAttribute("classCss", "alert alert-success");
            attributes.addFlashAttribute("message", " The Grade has been add to the Database !");

            //mailComponent.sendConfirmationMail(student);
        }
        return "redirect:/grades";
    }

    @RequestMapping(path = "/grades", method = RequestMethod.GET)
    public String getAllgrades(Model model) {
        model.addAttribute("grades", gradeRepository.findAll());
        return "grades";
    }

    @RequestMapping(path = "/grades/edit/{id}", method = RequestMethod.GET)
    public String editGrade(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("grade", gradeRepository.findById(id));

        List<Grade> gradeList = gradeRepository.findAll();
        model.addAttribute("gradeList", gradeList);

        List<Student> studentList = studentRepository.findAll();
        model.addAttribute("studentList", studentList);

        List<Course> courseList = courseRepository.findAll();
        model.addAttribute("courseList", courseList);


        return "editgrade";
    }

    @RequestMapping(path = "/grades/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable(value = "id") int id, RedirectAttributes attributes) {
        try {
            gradeRepository.deleteById(id);
            attributes.addFlashAttribute("classCss", "alert alert-warning");
            attributes.addFlashAttribute("message", "One Grade deleted from the Database !");
        } catch (Exception e) {
            e.getMessage();
        }

        return "redirect:/grades";
    }


}