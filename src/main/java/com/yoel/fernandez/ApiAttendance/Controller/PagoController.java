package com.yoel.fernandez.ApiAttendance.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    

    @PostMapping("/nuevoDTO")
    private ResponseEntity<String> crearPagoDTO(@RequestBody PagoDTO pago){
        pagoService.crearPagoDTO(pago);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pago creado con éxito");
    }

    @GetMapping("/listarDTO")
    private List<PagoDTO> listaPagosDTO(){
        return pagoService.listaPagosDTO();
    }



    @DeleteMapping("/eliminar/{codigo}")
    public void eliminarEmpleado(@PathVariable String codigo){
        pagoService.eliminarPago(codigo);
    }

    @PutMapping("/actualizar/{codigo}")
    public PagoDTO actualizarEmpleadoDTO(@PathVariable String codigo, @RequestBody  PagoDTO pagoDTO){
        return pagoService.actualPagoDTO(codigo, pagoDTO);
    }



    @GetMapping("/{codigo}")
    public PagoDTO retornarPorID(@PathVariable String codigo){
        return pagoService.retornarPorId(codigo);
    }

}
