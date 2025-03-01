package com.yoel.fernandez.ApiAttendance.Repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yoel.fernandez.ApiAttendance.Entity.GastosGenerales;

@Repository
public interface GastosGeneralesRepository extends JpaRepository<GastosGenerales, Integer>{

}
