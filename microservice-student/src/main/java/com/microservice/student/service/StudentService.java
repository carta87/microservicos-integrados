package com.microservice.student.service;

import com.library.entidades.dto.AttendantDTO;
import com.library.entidades.dto.StudentDTO;
import com.library.entidades.jpa.entity.Student;
import com.microservice.student.client.ReporteClient;
import com.microservice.student.repository.IStudendRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{

    private final IStudendRepository iStudendRepository;
    private final ReporteClient reporteClient;

    @Override
    public List<Student> findAll() {
        return (List<Student>) iStudendRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return iStudendRepository.findById(id).orElse(new Student());
    }

    @Override
    @Transactional
    public void save(Student student) {
        iStudendRepository.save(student);
        String result = reporteClient.reporteComprobante(StudentDTO.builder()
                .name(student.getName())
                .lastName(student.getLastName())
                .attendantDTO(AttendantDTO.builder()
                        .name(student.getAttendant().getName())
                        .lastName(student.getAttendant().getName())
                        .build())
                .build());
        log.info(result);
    }

    @Override
    public List<Student> findByIdCourse(Long idCourse) {
        return iStudendRepository.findAllStudent(idCourse);
    }
}
