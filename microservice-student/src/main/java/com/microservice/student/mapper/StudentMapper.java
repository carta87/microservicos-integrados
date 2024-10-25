package com.microservice.student.mapper;

import com.library.entidades.dto.AttendantDTO;
import com.library.entidades.dto.StudentDTO;
import com.library.entidades.jpa.entity.Attendant;
import com.library.entidades.jpa.entity.Student;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    List<StudentDTO> mapToDto(List<Student> students);

    StudentDTO mapToDto(Student student);

    Student mapToEntity(StudentDTO studentDTO);

    AttendantDTO mapToDto(Attendant attendant);
}
