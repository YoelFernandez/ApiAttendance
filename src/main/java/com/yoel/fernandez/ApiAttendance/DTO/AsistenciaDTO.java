package com.yoel.fernandez.ApiAttendance.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaDTO {
    private BigDecimal almuerzo;
    private LocalDate fecha;
    private LocalTime horaIngreso;
    private LocalTime horaSalida;
    private EmployedDTO employed;
    private ObraDTO obra;
}