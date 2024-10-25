package com.microservice.course.mapper;

import com.library.entidades.dto.CourseDTO;
import com.library.entidades.jpa.entity.Course;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    List<CourseDTO> mapToDto(List<Course> courseList);

    CourseDTO mapToDto(Course course);

    Course mapToEntity(CourseDTO courseDTO);
}
