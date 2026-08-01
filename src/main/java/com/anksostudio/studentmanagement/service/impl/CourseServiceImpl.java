package com.anksostudio.studentmanagement.service.impl;

import com.anksostudio.studentmanagement.dto.CourseDTO;
import com.anksostudio.studentmanagement.model.Courses;
import com.anksostudio.studentmanagement.repository.CourseRepository;
import com.anksostudio.studentmanagement.service.CourseService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;
    private final ModelMapper mapper;

    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper mapper) {
        this.courseRepository = courseRepository;
        this.mapper = mapper;
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        log.info("creating course with code: {}", courseDTO.getCourseCode());
        Courses courses = mapper.map(courseDTO, Courses.class);
        courseRepository.save(courses);
        return mapper.map(courses, CourseDTO.class);
    }

    @Override
    public boolean existsByCourseCode(String code) {
        log.info("checking if code exists: ()", code);
        return courseRepository.existsByCourseCodeIgnoreCase(code);
    }

    @Override
    public Page<CourseDTO> getCourses(int page, int size) {
        log.info("list of course from: {}", page);

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        return courseRepository.findByActiveTrue(pageRequest)
                .map(courses -> mapper.map(courses, CourseDTO.class));
    }

}
