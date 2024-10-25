package com.microservice.course.config;

import com.library.entidades.jpa.entity.CourseEntity;
import com.microservice.course.repository.ICourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChargeData {

    private final ICourseRepository iCourseRepository;

    @Bean
    public void cargarDatosIniciales() {

        List<CourseEntity> courseEntityList = (List<CourseEntity>) iCourseRepository.findAll();
        if(courseEntityList.isEmpty()){
            CourseEntity courseEntity1 = CourseEntity.builder()
                    .numberCourse(101L)
                    .name("Matematicas")
                    .teacher("Francisco Paez")
                    .build();

            CourseEntity courseEntity2 = CourseEntity.builder()
                    .numberCourse(102L)
                    .name("Español")
                    .teacher("Martha Suares")
                    .build();

            iCourseRepository.saveAll(List.of(courseEntity1, courseEntity2));
        }
    }
}
