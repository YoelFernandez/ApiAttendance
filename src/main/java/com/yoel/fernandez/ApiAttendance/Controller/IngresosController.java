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

import com.yoel.fernandez.ApiAttendance.DTO.IngresosDTO;
import com.yoel.fernandez.ApiAttendance.Service.IngresosService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ingresos")
public class IngresosController {
    private final IngresosService ingresosService;

    

    @GetMapping("/listarDTO")
    public List<IngresosDTO> listarIngresosDTO(){
        return ingresosService.listarIngresosDTO();
    }


    @PostMapping("/nuevoDTO")
    public ResponseEntity<String> crearIngreso(@RequestBody IngresosDTO ingresosDTO) {
        ingresosService.crearIngreso2(ingresosDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ingreso creado con éxito");
    }


    @DeleteMapping("/eliminar/{codigo}")
    public void eliminarEmpleado(@PathVariable String codigo){
        ingresosService.eliminarIngreso(codigo);
    }

    @PutMapping("/actualizar/{codigo}")
    public IngresosDTO actualizarEmpleadoDTO(@PathVariable String codigo, @RequestBody  IngresosDTO ingresosDTO){
        return ingresosService.actualIngresoDTO(codigo, ingresosDTO);
    }



    @GetMapping("/{codigo}")
    public IngresosDTO retornarPorID(@PathVariable String codigo){
        return ingresosService.retornarPorId(codigo);
    }




}
