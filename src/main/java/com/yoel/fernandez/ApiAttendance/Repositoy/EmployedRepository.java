package com.yoel.fernandez.ApiAttendance.Repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yoel.fernandez.ApiAttendance.Entity.Employed;

@Repository
public interface EmployedRepository extends JpaRepository<Employed, String>{
    Employed findByCorreoEmpleado(String correoEmpleado);
}
