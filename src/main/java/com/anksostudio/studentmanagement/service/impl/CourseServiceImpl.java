package com.anksostudio.studentmanagement.service.impl;

import com.anksostudio.studentmanagement.dto.CourseDTO;
import com.anksostudio.studentmanagement.model.Courses;
import com.anksostudio.studentmanagement.repository.CourseRepository;
import com.anksostudio.studentmanagement.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper mapper;

    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper mapper){
        this.courseRepository = courseRepository;
        this.mapper = mapper;
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
       Courses courses = mapper.map(courseDTO, Courses.class);
        courseRepository.save(courses);
        return mapper.map(courses, CourseDTO.class);
    }

    @Override
    public boolean existsByCourseCode(String code) {
        return courseRepository.existsByCourseCodeIgnoreCase(code);
    }
}
