package com.anksostudio.studentmanagement.service;

import com.anksostudio.studentmanagement.dto.CourseDTO;

public interface CourseService {

    CourseDTO createCourse(CourseDTO courseDTO);

    boolean existsByCourseCode(String code);
}
