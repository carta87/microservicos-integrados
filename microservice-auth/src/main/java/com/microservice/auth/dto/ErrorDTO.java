package com.microservice.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {

    public String code;
    public String message;
}
