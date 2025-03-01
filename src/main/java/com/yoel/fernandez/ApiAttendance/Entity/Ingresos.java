package com.yoel.fernandez.ApiAttendance.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Ingresos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ingresos {

    @Id
    private String codigoIngreso;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montoServicio;
    @Enumerated(EnumType.STRING)
    private medioPago mediPago;

    @Enumerated(EnumType.STRING)
    private estadoPago estadoPago;

    //@Column(updatable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate fechaIngresos;


    public enum medioPago{
        EFECTIVO, YAPE, PLIN, PAYPAL,TRANSFERENCIA, CHEQUE, CRIPTOMONEDAS
    }

    @ManyToOne
    @JoinColumn(name = "codigoServicio")
    @JsonBackReference("service-ingresos") // Nombre único
    private ServiceEntity service;

    public enum estadoPago{
        PENDIENTE, CANCELADO
    }

}
