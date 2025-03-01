package com.yoel.fernandez.ApiAttendance.Entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Employed")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employed {
    @Id
    private String codigoEmpleado;
    @Column(nullable = false, length = 100)
    private String nombresEmpleado;
    @Column(nullable = false, length = 100)
    private String apellidosEmpleado;

    @Enumerated(EnumType.STRING)
    private puestoEmpleado puestoEmpleado;


    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal sueldoEmpleado;

    @Column(nullable = false,unique = true, length = 15)
    private String telefonoEmpleado;

    @Column( nullable = false,unique = true, length = 50)
    private String correoEmpleado;  

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    private RolEmpleado role;


    public enum RolEmpleado {
        ADMIN, EMPLOYED
    }

    public enum puestoEmpleado{
        GERENTE, OPERARIO, OFICIAL, TECNICO, AYUDANTE, PEON
    }   

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Pago> pagos = new ArrayList<>();

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval= true)
    private List<Asistencia> asistencias = new ArrayList<>();

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval= true)
    private List<GastosGenerales> gastosGenerales = new ArrayList<>();

}


