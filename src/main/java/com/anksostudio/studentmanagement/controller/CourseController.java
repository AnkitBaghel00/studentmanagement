package com.anksostudio.studentmanagement.controller;


import com.anksostudio.studentmanagement.dto.CourseDTO;
import com.anksostudio.studentmanagement.repository.CourseRepository;
import com.anksostudio.studentmanagement.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseRepository courseRepository;
    private CourseService courseService;

    CourseController(CourseService courseService, CourseRepository courseRepository){
        this.courseService = courseService;
        this.courseRepository = courseRepository;
    }


    @GetMapping("/new")
    public String showCreateCourse(Model model){
        model.addAttribute("courseDto", new CourseDTO());
        return "add-course";
    }

    @GetMapping("/list")
    public String listCourses(){
        return "courses";
    }

    @PostMapping
    public String createCourse(@Valid @ModelAttribute("courseDTO") CourseDTO courseDTO,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "add-course";
        }

        if(courseService.existsByCourseCode(courseDTO.getCourseCode())){
            bindingResult.rejectValue("courseCode", null,"Code must be unique");
            return "add-course";
        }

        courseService.createCourse(courseDTO);
        redirectAttributes.addAttribute("message","Course is created successfully");

        return "/course/list";
    }
}
