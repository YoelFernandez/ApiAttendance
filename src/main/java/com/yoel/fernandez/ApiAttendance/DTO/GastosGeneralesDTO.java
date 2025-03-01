package com.yoel.fernandez.ApiAttendance.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GastosGeneralesDTO {
    private Integer idGastosGenerales;
    private String descripcionGastosGenerales;
    private BigDecimal montoGastosGenerales;
    private LocalDate fechaEntrega;
    private String medioPago;
    private String estadoPago;
    private String codigoObra;
    private String codigoEmpleado;

}
