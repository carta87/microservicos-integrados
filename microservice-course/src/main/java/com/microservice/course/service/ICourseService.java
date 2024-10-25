package com.microservice.course.service;

import com.library.entidades.dto.CourseDTO;
import com.library.entidades.http.response.StudentByCourseResponse;
import java.util.List;

public interface ICourseService {

    List<CourseDTO> findAll();

    CourseDTO findById(Long id);

    void save (CourseDTO course);

    void update(CourseDTO course);

    StudentByCourseResponse findStudentsByNumberCourse(Long numberCourse);

    void deleteById(Long id);
}
