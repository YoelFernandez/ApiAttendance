package com.yoel.fernandez.ApiAttendance.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "Pago")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 🔹 Auto-generación del código
    private Integer codigoPago;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montoPago; 

    @Enumerated(EnumType.STRING)
    private medioPago medioPago;

    @Enumerated(EnumType.STRING)
    private estadoPago estadoPago;
    
    @Column(updatable = false)
    @Temporal(TemporalType.DATE)   
    private LocalDate fechaPago;


    public enum medioPago{
        EFECTIVO, YAPE, PLIN, PAYPAL,TRANSFERENCIA, CHEQUE, CRIPTOMONEDAS
    }

    @ManyToOne
    @JoinColumn(name = "codigo_empleado")
    private Employed empleado;

    public enum estadoPago{
        PENDIENTE, CANCELADO
    }


}
