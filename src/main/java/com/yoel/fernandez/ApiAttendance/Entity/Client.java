package com.yoel.fernandez.ApiAttendance.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Client")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {

    @Id
    private String codCliente;

    @Column(nullable = false, length = 100)
    private String nombreCliente;

    @Column(unique = true, length = 15)
    private String telefonoCliente;

    @Column(unique = true, nullable = false)
    private String correoCliente;

    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false) // EL VALOR NO PUEDE SER MODIFICADO UNA VEZ QUE SE HA CREADO EN LA BD
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "cliente" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Obra> obras = new ArrayList<>();

}
