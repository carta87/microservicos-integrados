package com.microservice.student.mapper;

import com.library.entidades.dto.AttendantDTO;
import com.library.entidades.dto.StudentDTO;
import com.library.entidades.jpa.entity.AttendantEntity;
import com.library.entidades.jpa.entity.StudentEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    List<StudentDTO> mapToDto(List<StudentEntity> studentEntities);

    StudentDTO mapToDto(StudentEntity studentEntity);

    StudentEntity mapToEntity(StudentDTO studentDTO);

    AttendantDTO mapToDto(AttendantEntity attendantEntity);


}
