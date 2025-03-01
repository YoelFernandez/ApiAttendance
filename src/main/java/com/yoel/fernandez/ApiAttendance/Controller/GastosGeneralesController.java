package com.yoel.fernandez.ApiAttendance.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoel.fernandez.ApiAttendance.DTO.GastosGeneralesDTO;
import com.yoel.fernandez.ApiAttendance.Entity.GastosGenerales;
import com.yoel.fernandez.ApiAttendance.Service.GastosGeneralesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gastos-generales")
public class GastosGeneralesController {

    private final GastosGeneralesService gastosGeneralesService;

    @PostMapping("/nuevo")
    public void crearGastoGeneral(@RequestBody GastosGenerales gastosGenerales){
        gastosGeneralesService.nuevoGasto(gastosGenerales);
    }

    @GetMapping("/listar")
    public List<GastosGenerales> listarGastos(){
        return gastosGeneralesService.listaGastosGenerales();
    }
    
    @PostMapping("/nuevoDTO")
    public ResponseEntity<String> crearGastoGeneralDTO(@RequestBody GastosGeneralesDTO gastosGeneralesDTO){
        gastosGeneralesService.nuevoGastoDTO(gastosGeneralesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("ingreso creado correctamente");
    }

    @GetMapping("/listarDTO")
    public List<GastosGeneralesDTO> listarGastosDTO(){
        return gastosGeneralesService.listaGastosGeneralesDTO();
    }
}
