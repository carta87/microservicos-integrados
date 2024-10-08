package com.microservice.student.service;

import com.library.entidades.dto.StudentDTO;
import com.library.entidades.jpa.entity.Student;
import java.util.List;

public interface IStudentService {

    List<Student> findAll();

    Student findById(Long id);

    void save(Student student);

    List<StudentDTO> findByIdCourse(Long idCourse);

}
