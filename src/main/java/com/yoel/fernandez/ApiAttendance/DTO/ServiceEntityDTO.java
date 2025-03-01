package com.yoel.fernandez.ApiAttendance.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceEntityDTO {
    private String codigoServicio;
    private String nombreServicio;
    private String tipoServicio; // Se usará como String para facilitar el mapeo
}

