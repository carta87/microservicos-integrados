package com.microservice.course.service;

import com.library.entidades.http.response.StudentByCourseResponse;
import com.library.entidades.jpa.entity.Course;
import java.util.List;

public interface ICourseService {

    List<Course> findAll();

    Course findById(Long id);

    void save (Course course);

    StudentByCourseResponse findStudentsByIdCourse(Long idCourse);
}
