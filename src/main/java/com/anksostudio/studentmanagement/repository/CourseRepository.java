package com.anksostudio.studentmanagement.repository;

import com.anksostudio.studentmanagement.model.Courses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // Updated import
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Courses, Long> {

    boolean existsByCourseCodeIgnoreCase(String courseCode);

    Page<Courses> findByActiveTrue(Pageable pageable);

}