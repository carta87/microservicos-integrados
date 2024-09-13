package com.microservice_report.service;

import com.library.entidades.dto.StudentDTO;

public interface IComprobantePagoService {

    String generarReporteComprobantePago(StudentDTO studentDTO);
}
