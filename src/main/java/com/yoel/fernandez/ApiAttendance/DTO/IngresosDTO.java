package com.yoel.fernandez.ApiAttendance.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngresosDTO {
    private String codigoIngreso;
    private BigDecimal montoServicio;
    private String medioPago;
    private String estadoPago;
    private LocalDate fechaIngresos;
    private String codigoServicio; // Agregamos el código del servicio

}
