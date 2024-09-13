package com.microservice_report.service;

import com.library.entidades.dto.StudentDTO;
import com.microservice_report.utils.ConstantesReportes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComprobantePagoService implements IComprobantePagoService {
    @Override
    public String generarReporteComprobantePago(StudentDTO studentDTO) {

        int numeroComprobante = 1245;

        String destinationPath =
                ConstantesReportes.PATH_DESTINACION_PRINCIPAL
                        + "ReportGenrates.pdf";

        String filePath =
                ConstantesReportes.PATH_ORIGIN_PRINCIPAL
                        + "comprobantePago.jrxml";

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ConstantesReportes.DATE_FECHA_HORA);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(ConstantesReportes.NUMERO_COMPRONBANTE, String.valueOf(numeroComprobante++));
        parameters.put(ConstantesReportes.FECHA_COMPRONBANTE, formatter.format(date));
        parameters.put(ConstantesReportes.VALOR_PAGADO, new BigDecimal(30000));
        parameters.put(ConstantesReportes.MEDIO_PAGO, "Efectivo");
        parameters.put(ConstantesReportes.NOMBRE_ALUMNO, studentDTO.getName() + " " + studentDTO.getLastName());
        parameters.put(ConstantesReportes.NOMBRE_ACUDIENTE, studentDTO.getAttendantDTO().getName()+ " "+ studentDTO.getAttendantDTO().getLastName());
        parameters.put(ConstantesReportes.IMAGEN_DIR, ConstantesReportes.PATH_IMAGENES);


        try {
            JasperReport report = JasperCompileManager.compileReport(filePath);
            JasperPrint print =  print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(print, destinationPath);
            return ConstantesReportes.REPORTE_EXITOSO;
        } catch (JRException e) {
            return ConstantesReportes.REPORTE_FALLIDO;
        }
    }
}
