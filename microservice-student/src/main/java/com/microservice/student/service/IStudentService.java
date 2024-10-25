package com.microservice.student.service;

import com.library.entidades.dto.StudentDTO;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IStudentService {

    List<StudentDTO> findAll();

    StudentDTO findById(Long id);

    ResponseEntity<byte[]> save(StudentDTO studentDTO);

    List<StudentDTO> findByIdCourse(Long idCourse);

    boolean updateStudent(StudentDTO studentDTO);

    boolean  delete(Long id);
}
