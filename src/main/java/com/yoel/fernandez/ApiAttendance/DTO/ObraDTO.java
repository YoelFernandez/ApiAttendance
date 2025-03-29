package com.yoel.fernandez.ApiAttendance.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObraDTO {
    private String codObra;
    private String abreviatura;
    private String nombreProyecto;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private BigDecimal montoContratado;
    private String adicionales;
    private String urlImage;
    private ClientDTO cliente;
    private ServiceEntityDTO servicio;
}