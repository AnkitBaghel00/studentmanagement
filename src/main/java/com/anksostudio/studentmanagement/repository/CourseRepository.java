package com.anksostudio.studentmanagement.repository;

import com.anksostudio.studentmanagement.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Courses,Long> {

    boolean existsByCourseCodeIgnoreCase(String courseCode);
}
