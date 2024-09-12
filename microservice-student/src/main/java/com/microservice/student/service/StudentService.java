package com.microservice.student.service;

import com.microservice.student.entities.Student;
import com.microservice.student.repository.IStudendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{

    private final IStudendRepository iStudendRepository;

    @Override
    public List<Student> findAll() {
        return (List<Student>) iStudendRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return iStudendRepository.findById(id).orElse(new Student());
    }

    @Override
    public void save(Student student) {
        iStudendRepository.save(student);
    }

    @Override
    public List<Student> findByIdCourse(Long idCourse) {
        return iStudendRepository.findAllStudent(idCourse);
    }
}
