package com.yoel.fernandez.ApiAttendance.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "Service")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceEntity {
    @Id
    private String codigoServicio;

    @Column(nullable = false, length = 255)
    private String nombreServicio;

    @Enumerated(EnumType.STRING)
    private tipoServicio tipoServicio;

    public enum tipoServicio{
            GENERAL, COMPLEMENTARIO
    }

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("service-ingresos") // Mismo nombre
    private List<Ingresos> ingresos = new ArrayList<>();
    
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("service-obras") // Nombre único y descriptivo
    private List<Obra> obras = new ArrayList<>();
}
