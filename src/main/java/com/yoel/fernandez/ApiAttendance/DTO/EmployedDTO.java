package com.yoel.fernandez.ApiAttendance.DTO;

import java.math.BigDecimal;

import com.yoel.fernandez.ApiAttendance.Entity.Employed.RolEmpleado;
import com.yoel.fernandez.ApiAttendance.Entity.Employed.puestoEmpleado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployedDTO {
    private String codigoEmpleado;
    private String nombresEmpleado;
    private String apellidosEmpleado;
    private puestoEmpleado puestoEmpleado;
    private BigDecimal sueldoEmpleado;
    private String telefonoEmpleado;
    private String imageURL;
    private String correoEmpleado;
    private String password;
    private RolEmpleado role;

}