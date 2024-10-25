package com.microservice.course.mapper;

import com.library.entidades.dto.CourseDTO;
import com.library.entidades.jpa.entity.CourseEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    List<CourseDTO> mapToDto(List<CourseEntity> courseEntityList);

    CourseDTO mapToDto(CourseEntity courseEntity);

    CourseEntity mapToEntity(CourseDTO courseDTO);
}
