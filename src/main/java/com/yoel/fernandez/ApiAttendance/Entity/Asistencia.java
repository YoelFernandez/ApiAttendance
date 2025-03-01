package com.yoel.fernandez.ApiAttendance.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "Asistencia")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAsistencia;

    @Column(name = "almuerzo", nullable = true)
    private BigDecimal almuerzo;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "Fecha")
    private LocalDate fecha;

    @CreationTimestamp
    @Temporal(TemporalType.TIME)
    @Column(name = "hora_de_ingreso")
    private LocalTime horaIngreso;


    @Temporal(TemporalType.TIME)
    @Column(name = "hora_de_salida")
    private LocalTime horaSalida;


    @ManyToOne
    @JoinColumn(name = "codigo_empleado")
    @JsonBackReference // Evita la serialización infinita
    private Employed empleado;

    @ManyToOne
    @JsonBackReference // Evita la serialización infinita
    @JoinColumn(name = "codigo_obra")
    private Obra obra;


}
