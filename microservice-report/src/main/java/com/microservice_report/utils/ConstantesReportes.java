package com.microservice_report.utils;

import java.io.File;

public final class ConstantesReportes {
    public static final String PATH_DESTINACION_PRINCIPAL = "microservice-report" + File.separator +
            "src" + File.separator +
            "main"+ File.separator +
            "resources"+ File.separator +
            "static" + File.separator ;

    public static final String PATH_ORIGIN_PRINCIPAL = "microservice-report" + File.separator +
            "src" + File.separator +
            "main"+ File.separator +
            "resources"+ File.separator +
            "templates" + File.separator +
            "report" + File.separator;

    public static final String DATE_FECHA_HORA = "dd-MM-yyyy HH:mm:ss";

    public static final String NUMERO_COMPRONBANTE = "numCombante";

    public static final String FECHA_COMPRONBANTE = "fechaComprobante";
    public static final String VALOR_PAGADO = "valorPagado";

    public static final String MEDIO_PAGO = "medioPago";

    public static final String NOMBRE_ALUMNO = "nomAlumno";

    public static final String NOMBRE_ACUDIENTE = "nomAcudiente";

    public static final String IMAGEN_DIR = "imageDir";

    public static final String PATH_IMAGENES = "classpath:/static/images/";

    public static final String REPORTE_EXITOSO = "Creacion de reporte exitoso";
    public static final String REPORTE_FALLIDO = "Creacion de reporte fallida";
}
