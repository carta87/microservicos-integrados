package com.microservice.student.service.implementation;

import com.library.entidades.dto.AttendantDTO;
import com.library.entidades.dto.StudentDTO;
import com.library.entidades.jpa.entity.Student;
import com.microservice.student.client.ReporteClient;
import com.microservice.student.repository.IStudendRepository;
import com.microservice.student.service.IStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

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
    public List<StudentDTO> findByIdCourse(Long idCourse) {
        List<StudentDTO> studentDTOList = new ArrayList<>();

        iStudendRepository.findAllStudent(idCourse).forEach(student -> {
            StudentDTO elemento = StudentDTO.builder()
                    .name(student.getName())
                    .email(student.getEmail())
                    .attendantDTO(AttendantDTO.builder()
                            .courseId(student.getAttendant().getId())
                            .name(student.getAttendant().getName())
                            .lastName(student.getAttendant().getName())
                            .email(student.getAttendant().getEmail())
                            .build())
                    .build();
            studentDTOList.add(elemento);
        });
        return studentDTOList;

    }
}
