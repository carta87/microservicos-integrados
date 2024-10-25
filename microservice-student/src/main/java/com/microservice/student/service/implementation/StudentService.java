package com.microservice.student.service.implementation;

import com.library.entidades.dto.AttendantDTO;
import com.library.entidades.dto.StudentDTO;
import com.library.entidades.jpa.entity.Student;
import com.microservice.student.client.ReporteClient;
import com.microservice.student.mapper.StudentMapper;
import com.microservice.student.repository.IStudendRepository;
import com.microservice.student.service.IStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

    private final ReporteClient reporteClient;
    private final StudentMapper studentMapper;
    private final IStudendRepository iStudendRepository;

    @Override
    public List<StudentDTO> findAll() {
        return studentMapper.mapToDto((List<Student>) iStudendRepository.findAll());
    }

    @Override
    public StudentDTO findById(Long id) {
        return studentMapper.mapToDto(iStudendRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public  ResponseEntity<byte[]> save(StudentDTO studentDTO ) {
        iStudendRepository.save(studentMapper.mapToEntity(studentDTO));
        return reporteClient.reporteComprobante(StudentDTO.builder()
                .id(studentDTO.getId())
                .name(studentDTO.getName())
                .lastName(studentDTO.getLastName())
                .attendant(AttendantDTO.builder()
                        .name(studentDTO.getAttendant().getName())
                        .lastName(studentDTO.getAttendant().getLastName())
                        .build())
                .build());
    }

    @Override
    public List<StudentDTO> findByIdCourse(Long idCourse) {
        List<StudentDTO> studentDTOList = new ArrayList<>();
        iStudendRepository.findAllStudent(idCourse).forEach(student ->
            studentDTOList.add(StudentDTO.builder()
                    .name(student.getName())
                    .lastName(student.getLastName())
                    .email(student.getEmail())
                    .courseNumber(student.getCourseNumber())
                    .attendant(AttendantDTO.builder()
                            .id(student.getAttendant().getId())
                            .name(student.getAttendant().getName())
                            .lastName(student.getAttendant().getLastName())
                            .email(student.getAttendant().getEmail())
                            .build())
                    .build()));
        return studentDTOList;
    }

    @Override
    @Transactional
    public boolean updateStudent(StudentDTO studentDTO) {
        Optional<Student> existingStudent = iStudendRepository.findById(studentDTO.getId());
        if (existingStudent.isPresent()) {
            iStudendRepository.save(studentMapper.mapToEntity(studentDTO));
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Student> existingStudent = iStudendRepository.findById(id);
        if (existingStudent.isPresent()) {
            iStudendRepository.delete(existingStudent.get());
            return true;
        }
        return false;
    }
}
