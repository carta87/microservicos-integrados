package com.microservice_report.service;

import com.library.entidades.dto.StudentDTO;
import org.springframework.http.ResponseEntity;

public interface IComprobantePagoService {

    ResponseEntity<byte[]> generarReporteComprobantePago(StudentDTO studentDTO);
}
