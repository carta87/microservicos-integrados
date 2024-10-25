package com.library.entidades.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private Long id;
    private Long numberCourse;
    private String name;
    private String teacher;
}
