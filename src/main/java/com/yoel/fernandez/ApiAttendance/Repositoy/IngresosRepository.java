package com.yoel.fernandez.ApiAttendance.Repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yoel.fernandez.ApiAttendance.Entity.Ingresos;

@Repository
public interface IngresosRepository extends JpaRepository<Ingresos, String>{

}
