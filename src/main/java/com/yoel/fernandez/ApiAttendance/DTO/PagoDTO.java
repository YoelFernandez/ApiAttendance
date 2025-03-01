package com.yoel.fernandez.ApiAttendance.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {
    private String codigoPago;
    private BigDecimal montoPago;
    private String medioPago;
    private String estadoPago; 
    private LocalDate fechaPago;
    private String codigoEmpleado;
    
}
