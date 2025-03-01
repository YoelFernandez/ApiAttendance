package com.yoel.fernandez.ApiAttendance.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Obra")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Obra {

    @Id
    @Column(name = "cod_obra", nullable = false, unique = true)
    private String codObra;

    @Column(name = "abreviatura", length = 4, nullable = false)
    private String abreviatura;

    @Column(name = "nombre_Proyecto", length = 255, nullable = false)
    private String nombreProyecto;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_Inicio", nullable = false)
    private LocalDate fechaInicio;

    @Temporal(TemporalType.DATE)
    @Column(name = "Fecha_Fin")
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    private estadoObra estado;

    @Column(name = "monto_Contratado", precision = 15, scale = 2)
    private BigDecimal montoContratado;

    @Column(name = "Adicionales", length = 255)
    private String adicionales;

    @ManyToOne
    @JoinColumn(name = "codCliente")
    private Client cliente;

    @ManyToOne
    @JoinColumn(name = "codigoServicio")
    private ServiceEntity service;

    public enum estadoObra{
        PENDIENTE, EN_EJECUCION, CANCELADO, CULMINADO
    }

    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asistencia> asistencias = new ArrayList<>();

    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL, orphanRemoval= true)
    private List<GastosGenerales> gastosGenerales = new ArrayList<>();
}
