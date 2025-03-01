package com.yoel.fernandez.ApiAttendance.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {    
    private String codCliente;
    private String nombreCliente;
    private String correoCliente;
    private String telefonoCliente;
}
