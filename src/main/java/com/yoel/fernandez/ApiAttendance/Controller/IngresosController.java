package com.yoel.fernandez.ApiAttendance.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoel.fernandez.ApiAttendance.DTO.IngresosDTO;
import com.yoel.fernandez.ApiAttendance.Entity.Ingresos;
import com.yoel.fernandez.ApiAttendance.Service.IngresosService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ingresos")
public class IngresosController {
    private final IngresosService ingresosService;
    
    @PostMapping("/nuevo")
    public void crearIngreso(@RequestBody Ingresos ingresos){
        ingresosService.crearIngreso(ingresos);
    }

    @GetMapping("/listar")
    public List<Ingresos> listarIngresos(){
        return ingresosService.listarIngresos();
    }

    @GetMapping("/listarDTO")
    public List<IngresosDTO> listarIngresosDTO(){
        return ingresosService.listarIngresosDTO();
    }


    @PostMapping("/nuevoDTO")
    public ResponseEntity<String> crearIngreso(@RequestBody IngresosDTO ingresosDTO) {
        ingresosService.crearIngreso2(ingresosDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ingreso creado con éxito");
    }

}
