package com.microservice.course.service.implementation;

import com.library.entidades.dto.StudentDTO;
import com.library.entidades.http.response.StudentByCourseResponse;
import com.library.entidades.jpa.entity.Course;
import com.microservice.course.client.StudentClient;
import com.microservice.course.repository.ICourseRepository;
import com.microservice.course.service.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService implements ICourseService {

    private final ICourseRepository iCourseRepository;
    private final StudentClient studentClient;

    @Override
    public List<Course> findAll() {
        return (List<Course>) iCourseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return iCourseRepository.findById(id).orElse(new Course());
    }

    @Override
    public void save(Course course) {
        iCourseRepository.save(course);
    }

    @Override
    public StudentByCourseResponse findStudentsByIdCourse(Long idCourse) {

        //busqueda de los datos del curso
        Course course = iCourseRepository.findById(idCourse).orElse(new Course());

        //obtener los estudiantes del curso
        List<StudentDTO> students = studentClient.findAllStudentByCourse(idCourse);

        //construir el response
        return StudentByCourseResponse.builder()
                .courseName(course.getName())
                .teacher(course.getTeacher())
                .studentDTOList(students)
                .build();
    }
}
