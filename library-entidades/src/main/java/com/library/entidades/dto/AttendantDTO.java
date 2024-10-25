package com.library.entidades.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendantDTO {

    private Long id;
    private String name;
    private String lastName;
    private String email;
}
