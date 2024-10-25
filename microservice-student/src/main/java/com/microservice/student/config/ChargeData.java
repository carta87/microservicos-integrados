package com.microservice.student.config;

import com.library.entidades.jpa.entity.AttendantEntity;
import com.library.entidades.jpa.entity.StudentEntity;
import com.microservice.student.repository.IStudendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChargeData {

    private final IStudendRepository iStudendRepository;

    @Bean
    public void cargarDatosIniciales() {
        List<StudentEntity> entityList = (List<StudentEntity>) iStudendRepository.findAll();

        if(entityList.isEmpty()){
            StudentEntity studentEntity1 = StudentEntity.builder()
                    .name("Sara")
                    .lastName("Perez")
                    .email("sara@gmail.com")
                    .courseNumber(101L)
                    .attendant(AttendantEntity.builder()
                            .name("Pedro")
                            .lastName("Perez")
                            .email("pedro@gmail.com")
                            .build())
                    .build();

            StudentEntity studentEntity2 = StudentEntity.builder()
                    .name("Juliana")
                    .lastName("Arenas")
                    .email("juliana@gmail.com")
                    .courseNumber(101L)
                    .attendant(AttendantEntity.builder()
                            .name("Cindy")
                            .lastName("Arenas")
                            .email("cindy@gmail.com")
                            .build())
                    .build();

            StudentEntity studentEntity3 = StudentEntity.builder()
                    .name("Andres")
                    .lastName("Castro")
                    .email("andres@gmail.com")
                    .courseNumber(102L)
                    .attendant(AttendantEntity.builder()
                            .name("Ismael")
                            .lastName("Castro")
                            .email("ismael@gmail.com")
                            .build())
                    .build();

            iStudendRepository.saveAll(List.of(studentEntity1, studentEntity2, studentEntity3));
        }
    }
}
