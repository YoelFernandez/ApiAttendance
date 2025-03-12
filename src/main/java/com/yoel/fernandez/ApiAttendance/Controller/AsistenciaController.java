package com.yoel.fernandez.ApiAttendance.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoel.fernandez.ApiAttendance.DTO.AsistenciaDTO;
import com.yoel.fernandez.ApiAttendance.Entity.Asistencia;
import com.yoel.fernandez.ApiAttendance.Service.AsistenciaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/asistencia")
@RequiredArgsConstructor
public class AsistenciaController {
    private final AsistenciaService asistenciaService;
    
    @GetMapping("/listar")
    public List<Asistencia> listarAsistencias(){
        return asistenciaService.listarAsistencias();
    }

    @GetMapping("/listarDTO")
    public List<AsistenciaDTO> listarAsistenciasDTO(){
        return asistenciaService.listarAsistenciasDTO();
    }
    
    @PostMapping("/nuevoDTO")
    public AsistenciaDTO nuevAsistenciaDTO(@RequestBody AsistenciaDTO asistenciaDTO){
        return asistenciaService.crearAsistencia(asistenciaDTO);
    }


    
    @DeleteMapping("/eliminar/{codigo}")
    public void eliminarEmpleado(@PathVariable Integer codigo){
        asistenciaService.eliminarAsistencia(codigo);
    }

    @PutMapping("/actualizar/{codigo}")
    public AsistenciaDTO actualizarEmpleadoDTO(@PathVariable Integer codigo, @RequestBody  AsistenciaDTO asistenciaDTO){
        return asistenciaService.actualizarAsistenciaDTO(codigo, asistenciaDTO);
    }



    @GetMapping("/{codigo}")
    public AsistenciaDTO retornarPorID(@PathVariable Integer codigo){
        return asistenciaService.retornarPorId(codigo);
    }


}
