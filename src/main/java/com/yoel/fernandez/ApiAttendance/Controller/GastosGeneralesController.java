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

import com.yoel.fernandez.ApiAttendance.DTO.GastosGeneralesDTO;
import com.yoel.fernandez.ApiAttendance.Service.GastosGeneralesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gastos-generales")
public class GastosGeneralesController {

    private final GastosGeneralesService gastosGeneralesService;


    
    @PostMapping("/nuevoDTO")
    public ResponseEntity<String> crearGastoGeneralDTO(@RequestBody GastosGeneralesDTO gastosGeneralesDTO){
        gastosGeneralesService.nuevoGastoDTO(gastosGeneralesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Gasto creado correctamente");
    }

    @GetMapping("/listarDTO")
    public List<GastosGeneralesDTO> listarGastosDTO(){
        return gastosGeneralesService.listaGastosGeneralesDTO();
    }


    @DeleteMapping("/eliminar/{codigo}")
    public void eliminarGasto(@PathVariable Integer codigo){
        gastosGeneralesService.eliminarGasto(codigo);
    }

    @PutMapping("/actualizar/{codigo}")
    public GastosGeneralesDTO actualizarEmpleadoDTO(@PathVariable Integer codigo, @RequestBody  GastosGeneralesDTO gastosGeneralesDTO){
        return gastosGeneralesService.actualGastoGeneralDTO(codigo, gastosGeneralesDTO);
    }



    @GetMapping("/{codigo}")
    public GastosGeneralesDTO retornarPorID(@PathVariable Integer codigo){
        return gastosGeneralesService.retornarPorId(codigo);
    }
}
