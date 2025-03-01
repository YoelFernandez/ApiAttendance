package com.yoel.fernandez.ApiAttendance.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoel.fernandez.ApiAttendance.DTO.PagoDTO;
import com.yoel.fernandez.ApiAttendance.Entity.Pago;
import com.yoel.fernandez.ApiAttendance.Service.PagoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pago")
@RequiredArgsConstructor
public class PagoController {
    private final PagoService pagoService;

    
    @PostMapping("/nuevo")
    private void crearPago(@RequestBody Pago pago){
        pagoService.crearPago(pago);
    }

    @GetMapping("/listar")
    private List<Pago> listaPagos(){
        return pagoService.listaPagos();
    }

    @PostMapping("/nuevoDTO")
    private ResponseEntity<String> crearPagoDTO(@RequestBody PagoDTO pago){
        pagoService.crearPagoDTO(pago);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pago creado con éxito");
    }

    @GetMapping("/listarDTO")
    private List<PagoDTO> listaPagosDTO(){
        return pagoService.listaPagosDTO();
    }

}
