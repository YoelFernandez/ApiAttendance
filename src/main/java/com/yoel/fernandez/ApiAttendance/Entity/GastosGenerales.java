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
@Table(name = "Gastos_Generales")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GastosGenerales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGastosGenerales;

    @Column(name = "descripcion_gastos_generales", length = 255)
    private String descripcionGastosGenerales;

    @Column(name ="monto_gastos_generales", precision = 10, scale = 3)
    private BigDecimal montoGastosGenerales;


    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    @Enumerated(EnumType.STRING)
    private medioPago medioPago;

    @Enumerated(EnumType.STRING)
    private estadoPago estadoPago;

    public enum medioPago{
        EFECTIVO, YAPE, PLIN, PAYPAL,TRANSFERENCIA, CHEQUE, CRIPTOMONEDAS
    }

    public enum estadoPago{
        PENDIENTE, CANCELADO
    }

    @ManyToOne
    @JoinColumn(name = "codigo_obra")
    private Obra obra;

    @ManyToOne
    @JoinColumn(name = "codigo_empleado")
    private Employed empleado;

}
