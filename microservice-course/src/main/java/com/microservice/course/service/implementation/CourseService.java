package com.microservice.course.service.implementation;

import com.library.entidades.dto.CourseDTO;
import com.library.entidades.dto.StudentDTO;
import com.library.entidades.http.response.StudentByCourseResponse;
import com.library.entidades.jpa.entity.Course;
import com.microservice.course.client.StudentClient;
import com.microservice.course.exception.exception.DuplicateNumberCourseException;
import com.microservice.course.mapper.CourseMapper;
import com.microservice.course.repository.ICourseRepository;
import com.microservice.course.service.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService implements ICourseService {

    private final CourseMapper courseMapper;
    private final StudentClient studentClient;
    private final ICourseRepository iCourseRepository;


    @Override
    public List<CourseDTO> findAll() {
        return courseMapper.mapToDto((List<Course>) iCourseRepository.findAll());
    }

    @Override
    public CourseDTO findById(Long id) {
        return courseMapper.mapToDto(iCourseRepository.findById(id).orElse(new Course()));
    }

    @Override
    @Transactional
    public void save(CourseDTO courseDTO) {
        if (iCourseRepository.existsByNumberCourse(courseDTO.getNumberCourse())) {
            throw new DuplicateNumberCourseException("El número de curso " + courseDTO.getNumberCourse() + " esta registrado.", HttpStatus.BAD_REQUEST);
        }
        iCourseRepository.save(courseMapper.mapToEntity(courseDTO));
    }

    @Override
    @Transactional
    public void update(CourseDTO courseDTO) {
        //Verificar si el number_course ya existe
         if (iCourseRepository.existsByNumberCourse(courseDTO.getNumberCourse())) {
             iCourseRepository.save(courseMapper.mapToEntity(courseDTO));
         }else {
             throw new DuplicateNumberCourseException("Debe actualizar número de curso " + courseDTO.getNumberCourse() + " registrado.", HttpStatus.BAD_REQUEST);
         }
    }

    @Override
    public StudentByCourseResponse findStudentsByNumberCourse(Long numberCourse) {

        //busqueda de los datos del curso
        Course course = iCourseRepository.findByNumberCourse(numberCourse).orElse(new Course());

        //obtener los estudiantes del curso
        List<StudentDTO> students = studentClient.findAllStudentByCourse(numberCourse);

        //construir el response
        return StudentByCourseResponse.builder()
                .courseName(course.getName())
                .teacher(course.getTeacher())
                .studentDTOList(students)
                .build();
    }

    @Override
    public void deleteById(Long id) {
        iCourseRepository.deleteById(id);
    }
}
